package com.framework.retry;

import java.util.Set;

public final class RetryableStatusCode {

    private RetryableStatusCode() {
    }

    private static final Set<Integer> RETRYABLE_CODES = Set.of(
            429,
            500,
            502,
            503,
            504
    );

    public static boolean isRetryable(int statusCode) {

        return RETRYABLE_CODES.contains(statusCode);

    }

}