public class FraudCheckResult {
    private boolean rejected;
    private String reason;
    private double riskScore;

    public FraudCheckResult(boolean rejected, String reason, double riskScore) {
        this.rejected = rejected;
        this.reason = reason;
        this.riskScore = riskScore;
    }

    public boolean isRejected() {
        return rejected;
    }

    public String getReason() {
        return reason;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public static FraudCheckResult approved() {
        return new FraudCheckResult(
                false,      // rejected = false
                null,       // no rejection reason
                0.0         // low / no risk score
        );
    }

    public static FraudCheckResult rejected(String reason) {
        return new FraudCheckResult(
                true,       // rejected
                reason,     // rejection reason
                1.0         // high risk score
        );
    }


}