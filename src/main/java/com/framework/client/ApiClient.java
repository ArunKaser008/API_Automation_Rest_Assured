package com.framework.client;

import com.framework.context.FrameworkContext;
import com.framework.core.executor.HttpExecutor;
import com.framework.core.executor.HttpExecutorRegistry;
import com.framework.enums.HttpMethod;
import com.framework.models.ApiRequest;
import com.framework.retry.DefaultRetryPolicy;
import com.framework.retry.RetryExecutor;
import com.framework.retry.RetryPolicy;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Enterprise API Client.
 */
public class ApiClient {

    private final RequestSpecification requestSpecification;

    private final RetryExecutor retryExecutor;

    public ApiClient(RequestSpecification requestSpecification) {

        this.requestSpecification = requestSpecification;

        RetryPolicy retryPolicy = createRetryPolicy();

        this.retryExecutor =
                new RetryExecutor(retryPolicy);

    }

    public Response get(ApiRequest apiRequest) {

        return execute(HttpMethod.GET, apiRequest);

    }

    public Response post(ApiRequest apiRequest) {

        return execute(HttpMethod.POST, apiRequest);

    }

    public Response put(ApiRequest apiRequest) {

        return execute(HttpMethod.PUT, apiRequest);

    }

    public Response patch(ApiRequest apiRequest) {

        return execute(HttpMethod.PATCH, apiRequest);

    }

    public Response delete(ApiRequest apiRequest) {

        return execute(HttpMethod.DELETE, apiRequest);

    }

    /**
     * Common execution engine.
     */
    private Response execute(HttpMethod httpMethod,
                             ApiRequest apiRequest) {

        HttpExecutor executor =
                HttpExecutorRegistry.get(httpMethod);

        if (FrameworkContext.getInstance()
                .config()
                .isRetryEnabled()) {

            return retryExecutor.execute(() ->
                    executor.execute(
                            requestSpecification,
                            apiRequest));

        }

        return executor.execute(
                requestSpecification,
                apiRequest);

    }

    /**
     * Creates retry policy from configuration.
     */
    private RetryPolicy createRetryPolicy() {

        return new DefaultRetryPolicy(

                FrameworkContext.getInstance()
                        .config()
                        .getRetryMaxAttempts(),

                FrameworkContext.getInstance()
                        .config()
                        .getRetryInterval()

        );

    }

}