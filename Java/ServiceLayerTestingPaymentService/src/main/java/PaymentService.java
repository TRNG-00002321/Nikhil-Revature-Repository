import java.math.BigDecimal;

public class PaymentService
{
    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;
    private final TransactionLogger transactionLogger;
    private final FraudDetectionService fraudService;
    private final RetryConfig retryConfig;

    public PaymentService(PaymentGateway gateway, OrderRepository orderRepo,
                          TransactionLogger logger, FraudDetectionService fraudService,
                          RetryConfig retryConfig) {
        this.paymentGateway = gateway;
        this.orderRepository = orderRepo;
        this.transactionLogger = logger;
        this.fraudService = fraudService;
        this.retryConfig = retryConfig;
    }

    /**
     * Process payment for an order.
     *
     * Flow:
     * 1. Validate order exists and is pending
     * 2. Run fraud check
     * 3. Charge payment gateway
     * 4. Update order status
     * 5. Log transaction
     * 6. Return result
     */
    public PaymentResult processPayment(Long orderId, PaymentDetails paymentDetails) {
        // 1. Validate order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStateException(
                    "Cannot process payment for order in state: " + order.getStatus());
        }

        // 2. Fraud check
        FraudCheckResult fraudResult = fraudService.checkTransaction(
                paymentDetails.getCardNumber(),
                order.getTotal()
        );

        if (fraudResult.isRejected()) {
            order.setStatus(OrderStatus.FRAUD_SUSPECTED);
            orderRepository.save(order);
            transactionLogger.logRejected(orderId, "Fraud detected: " + fraudResult.getReason());
            throw new FraudDetectedException(fraudResult.getReason());
        }

        // 3. Process payment with retry
        PaymentResult result = processWithRetry(order.getTotal(), paymentDetails);

        // 4. Update order status
        if (result.isSuccessful()) {
            order.setStatus(OrderStatus.PAID);
            order.setTransactionId(result.getTransactionId());
        } else {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
        }
        orderRepository.save(order);

        // 5. Log transaction
        transactionLogger.log(orderId, result);

        return result;
    }

    private PaymentResult processWithRetry(BigDecimal amount, PaymentDetails details) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < retryConfig.getMaxAttempts()) {
            try {
                return paymentGateway.charge(amount, details);
            } catch (PaymentTimeoutException e) {
                lastException = e;
                attempts++;
                if (attempts < retryConfig.getMaxAttempts()) {
                    sleep(retryConfig.getRetryDelayMs());
                }
            }
        }

        throw new PaymentProcessingException(
                "Payment failed after " + attempts + " attempts", lastException);
    }

    /**
     * Refund a previously processed payment.
     */
    public RefundResult refundPayment(Long orderId, BigDecimal amount, String reason) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));

        if (order.getTransactionId() == null) {
            throw new InvalidOperationException("Order has no payment to refund");
        }

        if (amount.compareTo(order.getTotal()) > 0) {
            throw new InvalidOperationException("Refund amount exceeds order total");
        }

        RefundResult result = paymentGateway.refund(order.getTransactionId(), amount);

        if (result.isSuccessful()) {
            if (amount.equals(order.getTotal())) {
                order.setStatus(OrderStatus.REFUNDED);
            } else {
                order.setStatus(OrderStatus.PARTIALLY_REFUNDED);
            }
            orderRepository.save(order);
        }

        transactionLogger.logRefund(orderId, amount, reason, result);

        return result;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}