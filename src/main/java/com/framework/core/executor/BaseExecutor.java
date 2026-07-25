package com.framework.core.executor;

import com.framework.auth.AuthStrategyFactory;
import com.framework.models.ApiRequest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * Abstract base class for all HTTP executors.
 * Responsible for preparing the Rest Assured request.
 */
public abstract class BaseExecutor implements HttpExecutor {

    /**
     * Builds RequestSpecification from ApiRequest.
     *
     * @param requestSpecification Default Request Specification
     * @param apiRequest API Request
     * @return Prepared RequestSpecification
     */
    protected RequestSpecification prepareRequest(
            RequestSpecification requestSpecification,
            ApiRequest apiRequest) {

        RequestSpecification request =
                RestAssured.given(requestSpecification);

        applyHeaders(request, apiRequest);

        applyQueryParameters(request, apiRequest);

        applyPathParameters(request, apiRequest);

        applyRequestBody(request, apiRequest);

        applyAuthentication(request, apiRequest);

        return request;
    }

    /**
     * Applies request headers.
     */
    private void applyHeaders(RequestSpecification request,
                              ApiRequest apiRequest) {

        if (!apiRequest.getHeaders().isEmpty()) {
            request.headers(apiRequest.getHeaders());
        }

    }

    /**
     * Applies query parameters.
     */
    private void applyQueryParameters(RequestSpecification request,
                                      ApiRequest apiRequest) {

        if (!apiRequest.getQueryParams().isEmpty()) {
            request.queryParams(apiRequest.getQueryParams());
        }

    }

    /**
     * Applies path parameters.
     */
    private void applyPathParameters(RequestSpecification request,
                                     ApiRequest apiRequest) {

        if (!apiRequest.getPathParams().isEmpty()) {
            request.pathParams(apiRequest.getPathParams());
        }

    }

    /**
     * Applies request body.
     */
    private void applyRequestBody(RequestSpecification request,
                                  ApiRequest apiRequest) {

        if (apiRequest.getBody() != null) {
            request.body(apiRequest.getBody());
        }

    }

    /**
     * Applies authentication strategy.
     */
    private void applyAuthentication(RequestSpecification request,
                                     ApiRequest apiRequest) {

        AuthStrategyFactory
                .get(apiRequest.getAuthType())
                .apply(request, apiRequest);

    }

}