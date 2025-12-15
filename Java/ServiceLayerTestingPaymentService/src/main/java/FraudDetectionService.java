import java.math.BigDecimal;

public interface FraudDetectionService {
    FraudCheckResult checkTransaction(String cardNumber, BigDecimal amount);
}