package com.framework.core.executor;

import com.framework.models.ApiRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Abstract base class for HTTP Executors.
 */
public abstract class BaseExecutor implements HttpExecutor {

    protected RequestSpecification prepareRequest(RequestSpecification requestSpecification,
                                                  ApiRequest apiRequest) {

        RequestSpecification request = RestAssured.given(requestSpecification);

        if (!apiRequest.getHeaders().isEmpty()) {
            request.headers(apiRequest.getHeaders());
        }

        if (!apiRequest.getQueryParams().isEmpty()) {
            request.queryParams(apiRequest.getQueryParams());
        }

        if (!apiRequest.getPathParams().isEmpty()) {
            request.pathParams(apiRequest.getPathParams());
        }

        if (apiRequest.getBody() != null) {
            request.body(apiRequest.getBody());
        }

        return request;
    }

    @Override
    public abstract Response execute(RequestSpecification requestSpecification,
                                     ApiRequest apiRequest);
}