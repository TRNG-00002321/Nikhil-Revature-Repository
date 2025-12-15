import java.math.BigDecimal;

public interface TransactionLogger {
    void log(Long orderId, PaymentResult result);
    void logRejected(Long orderId, String reason);
    void logRefund(Long orderId, BigDecimal amount, String reason, RefundResult result);
}