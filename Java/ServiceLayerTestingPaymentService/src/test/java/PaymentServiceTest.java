import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock private PaymentGateway paymentGateway;
    @Mock private OrderRepository orderRepository;
    @Mock private TransactionLogger transactionLogger;
    @Mock private FraudDetectionService fraudService;
    @Mock private RetryConfig retryConfig;

    @InjectMocks private PaymentService paymentService;

    private Order testOrder;
    private PaymentDetails testPaymentDetails;

    @BeforeEach
    void setUp() {
        testOrder = createTestOrder();
        testPaymentDetails = createTestPaymentDetails();

        lenient().when(retryConfig.getMaxAttempts()).thenReturn(3);
        lenient().when(retryConfig.getRetryDelayMs()).thenReturn(0L);
    }

    /**
     * Creates a test order with default values for testing purposes.
     */
    private Order createTestOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(new BigDecimal("99.99"));
        order.setTransactionId(null);
        return order;
    }

    /**
     * Creates test payment details with sample credit card information.
     */
    private PaymentDetails createTestPaymentDetails() {
        return new PaymentDetails(
                "4532015112830366",  // Test Visa card number
                "John Doe",
                "12/25",
                "123"
        );
    }

    /**
     * Creates a test order with custom values.
     */
    private Order createTestOrder(Long id, OrderStatus status, BigDecimal total) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setTotal(total);
        order.setTransactionId(null);
        return order;
    }

    /**
     * Creates a paid test order with transaction ID.
     */
    private Order createPaidTestOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PAID);
        order.setTotal(new BigDecimal("99.99"));
        order.setTransactionId("TXN-123456789");
        return order;
    }

    @Test
    void processPayment_validOrder_chargesGateway() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        verify(paymentGateway).charge(testOrder.getTotal(), testPaymentDetails);
    }

    @Test
    void processPayment_successful_updatesOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        assertEquals(OrderStatus.PAID, testOrder.getStatus());
        assertEquals("TXN-1", testOrder.getTransactionId());
    }

    @Test
    void processPayment_successful_logsTransaction() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        PaymentResult result = PaymentResult.success("TXN-1");
        when(paymentGateway.charge(any(), any())).thenReturn(result);

        paymentService.processPayment(1L, testPaymentDetails);

        verify(transactionLogger).log(1L, result);
    }

    @Test
    void processPayment_successful_returnsTransactionId() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        PaymentResult result = paymentService.processPayment(1L, testPaymentDetails);

        assertTrue(result.isSuccessful());
        assertEquals("TXN-1", result.getTransactionId());
    }

    @Test
    void processPayment_orderNotFound_throwsException() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));
    }

    @Test
    void processPayment_orderAlreadyPaid_throwsException() {
        Order paidOrder = createPaidTestOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(paidOrder));

        assertThrows(InvalidOrderStateException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));
    }

    @Test
    void processPayment_orderCancelled_throwsException() {
        Order cancelled = createTestOrder(1L, OrderStatus.CANCELLED, new BigDecimal("50.00"));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(cancelled));

        assertThrows(InvalidOrderStateException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));
    }

    @Test
    void processPayment_fraudDetected_rejectsPayment() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.rejected("Stolen card"));

        assertThrows(FraudDetectedException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));
    }

    @Test
    void processPayment_fraudDetected_updatesOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.rejected("Fraud"));

        assertThrows(FraudDetectedException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));

        assertEquals(OrderStatus.FRAUD_SUSPECTED, testOrder.getStatus());
    }

    @Test
    void processPayment_fraudDetected_logsRejection() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.rejected("Fraud"));

        assertThrows(FraudDetectedException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));

        verify(transactionLogger)
                .logRejected(eq(1L), contains("Fraud"));
    }

    @Test
    void processPayment_fraudCheckPasses_proceedsToPayment() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        verify(paymentGateway).charge(any(), any());
    }

    @Test
    void processPayment_firstAttemptSucceeds_noRetry() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        verify(paymentGateway, times(1)).charge(any(), any());
    }

    @Test
    void processPayment_timeoutThenSuccess_retries() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());

        when(paymentGateway.charge(any(), any()))
                .thenThrow(new PaymentTimeoutException("Timeout"))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        verify(paymentGateway, times(2)).charge(any(), any());
    }

    @Test
    void processPayment_allAttemptsFail_throwsException() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());

        when(paymentGateway.charge(any(), any()))
                .thenThrow(new PaymentTimeoutException("Timeout"));

        assertThrows(PaymentProcessingException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));
    }

    @Test
    void processPayment_maxRetries_attemptsCorrectNumber() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());

        when(paymentGateway.charge(any(), any()))
                .thenThrow(new PaymentTimeoutException("Timeout"));

        assertThrows(PaymentProcessingException.class,
                () -> paymentService.processPayment(1L, testPaymentDetails));

        verify(paymentGateway, times(3)).charge(any(), any());
    }

    @Test
    void refundPayment_fullRefund_updatesStatusToRefunded() {
        Order paid = createPaidTestOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(paid));
        when(paymentGateway.refund(anyString(), any()))
                .thenReturn(RefundResult.success());

        paymentService.refundPayment(1L, paid.getTotal(), "Customer request");

        assertEquals(OrderStatus.REFUNDED, paid.getStatus());
    }

    @Test
    void refundPayment_partialRefund_updatesStatusToPartiallyRefunded() {
        Order paid = createPaidTestOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(paid));
        when(paymentGateway.refund(anyString(), any()))
                .thenReturn(RefundResult.success());

        paymentService.refundPayment(1L, new BigDecimal("20.00"), "Partial");

        assertEquals(OrderStatus.PARTIALLY_REFUNDED, paid.getStatus());
    }

    @Test
    void refundPayment_noTransaction_throwsException() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        assertThrows(InvalidOperationException.class,
                () -> paymentService.refundPayment(1L, BigDecimal.TEN, "Test"));
    }

    @Test
    void refundPayment_exceedsTotal_throwsException() {
        Order paid = createPaidTestOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(paid));

        assertThrows(InvalidOperationException.class,
                () -> paymentService.refundPayment(1L, new BigDecimal("200"), "Test"));
    }

    @Test
    void refundPayment_successful_logsRefund() {
        Order paid = createPaidTestOrder();
        RefundResult result = RefundResult.success();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(paid));
        when(paymentGateway.refund(anyString(), any())).thenReturn(result);

        paymentService.refundPayment(1L, BigDecimal.TEN, "Reason");

        verify(transactionLogger)
                .logRefund(1L, BigDecimal.TEN, "Reason", result);
    }

    @Test
    void processPayment_fraudCheck_happensBeforePayment() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        InOrder inOrder = inOrder(fraudService, paymentGateway);
        inOrder.verify(fraudService).checkTransaction(anyString(), any());
        inOrder.verify(paymentGateway).charge(any(), any());
    }

    @Test
    void processPayment_savesOrderWithCorrectStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(fraudService.checkTransaction(anyString(), any()))
                .thenReturn(FraudCheckResult.approved());
        when(paymentGateway.charge(any(), any()))
                .thenReturn(PaymentResult.success("TXN-1"));

        paymentService.processPayment(1L, testPaymentDetails);

        verify(orderRepository).save(argThat(
                o -> o.getStatus() == OrderStatus.PAID
        ));
    }

}