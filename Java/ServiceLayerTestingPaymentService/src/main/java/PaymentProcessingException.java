public class PaymentProcessingException extends RuntimeException {
    public PaymentProcessingException(String message, Exception lastException) {
        super(message);
    }
}
