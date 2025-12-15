import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentResult charge(BigDecimal amount, PaymentDetails details)
            throws PaymentTimeoutException;

    RefundResult refund(String transactionId, BigDecimal amount);
}