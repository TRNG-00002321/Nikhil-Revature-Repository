public class PaymentResult {
    private boolean successful;
    private String transactionId;
    private String errorMessage;

    public PaymentResult(boolean successful, String transactionId, String errorMessage) {
        this.successful = successful;
        this.transactionId = transactionId;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static PaymentResult success(String transactionId) {
        return new PaymentResult(
                true,           // successful
                transactionId,  // transaction id
                null             // no error
        );
    }

    public static PaymentResult failure(String errorMessage) {
        return new PaymentResult(
                false,          // unsuccessful
                null,           // no transaction id
                errorMessage
        );
    }

}