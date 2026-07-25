package com.framework.retry;

public class DefaultRetryPolicy implements RetryPolicy {

    private final int maxAttempts;

    private final long retryInterval;

    public DefaultRetryPolicy() {

        this(3, 2000);

    }

    public DefaultRetryPolicy(int maxAttempts,
                              long retryInterval) {

        this.maxAttempts = maxAttempts;
        this.retryInterval = retryInterval;

    }

    @Override
    public int maxAttempts() {
        return maxAttempts;
    }

    @Override
    public long retryInterval() {
        return retryInterval;
    }

}