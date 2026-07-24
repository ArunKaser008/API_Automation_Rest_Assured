package com.framework.client;

import com.framework.core.executor.HttpExecutor;
import com.framework.core.executor.HttpExecutorRegistry;
import com.framework.models.ApiRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Generic HTTP client responsible for executing REST requests.
 */
public class ApiClient {

    private final RequestSpecification requestSpecification;

    /**
     * Creates ApiClient with default Request Specification.
     */
    public ApiClient() {

        this.requestSpecification =
                RequestSpecificationFactory.getDefaultRequestSpecification();

    }

    /**
     * Creates ApiClient with custom Request Specification.
     *
     * @param requestSpecification Request Specification
     */
    public ApiClient(RequestSpecification requestSpecification) {

        this.requestSpecification = requestSpecification;

    }

    public Response get(ApiRequest request) {

        return execute(HttpMethod.GET, request);

    }

    public Response post(ApiRequest request) {

        return execute(HttpMethod.POST, request);

    }

    public Response put(ApiRequest request) {

        return execute(HttpMethod.PUT, request);

    }

    public Response patch(ApiRequest request) {

        return execute(HttpMethod.PATCH, request);

    }

    public Response delete(ApiRequest request) {

        return execute(HttpMethod.DELETE, request);

    }

    /**
     * Generic HTTP execution.
     */
    private Response execute(HttpMethod httpMethod,
                             ApiRequest apiRequest) {

        HttpExecutor executor =
                HttpExecutorRegistry.get(httpMethod);

        return executor
                .execute(requestSpecification, apiRequest)
                .then()
                .spec(ResponseSpecificationFactory.createDefault())
                .extract()
                .response();

    }

}