public class RefundResult {
    private boolean successful;
    private String refundId;
    private String errorMessage;

    public RefundResult(boolean successful, String refundId, String errorMessage) {
        this.successful = successful;
        this.refundId = refundId;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getRefundId() {
        return refundId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static RefundResult success() {
        return new RefundResult(
                true,        // successful
                "RFD-" + System.currentTimeMillis(), // simple generated refund id
                null         // no error
        );
    }

    public static RefundResult failure(String errorMessage) {
        return new RefundResult(
                false,       // unsuccessful
                null,        // no refund id
                errorMessage
        );
    }


}