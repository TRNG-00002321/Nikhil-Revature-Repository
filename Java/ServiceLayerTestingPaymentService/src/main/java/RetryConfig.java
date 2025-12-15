public class RetryConfig {
    private int maxAttempts;
    private long retryDelayMs;

    public RetryConfig(int maxAttempts, long retryDelayMs) {
        this.maxAttempts = maxAttempts;
        this.retryDelayMs = retryDelayMs;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long getRetryDelayMs() {
        return retryDelayMs;
    }
}