package com.framework.retry;

import io.restassured.response.Response;

import java.util.function.Supplier;

public final class RetryExecutor {

    private final RetryPolicy retryPolicy;

    public RetryExecutor(RetryPolicy retryPolicy) {

        this.retryPolicy = retryPolicy;

    }

    public Response execute(Supplier<Response> supplier) {

        Response response = null;

        int attempt = 1;

        while (attempt <= retryPolicy.maxAttempts()) {

            response = supplier.get();

            if (!RetryableStatusCode.isRetryable(
                    response.statusCode())) {

                return response;

            }

            if (attempt == retryPolicy.maxAttempts()) {

                return response;

            }

            try {

                Thread.sleep(retryPolicy.retryInterval());

            } catch (InterruptedException exception) {

                Thread.currentThread().interrupt();

                throw new RuntimeException(exception);

            }

            attempt++;

        }

        return response;

    }

}