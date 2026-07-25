package com.framework.retry;

public interface RetryPolicy {

    int maxAttempts();

    long retryInterval();

}