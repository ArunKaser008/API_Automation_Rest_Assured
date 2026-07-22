package com.framework.client;

import com.framework.core.executor.HttpExecutorRegistry;
import com.framework.core.request.ApiRequest;
import com.framework.specifications.RequestSpecificationFactory;
import com.framework.specifications.ResponseSpecificationFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiClient {

    public Response get(ApiRequest request) {
        return execute(request);
    }

    public Response post(ApiRequest request) {
        return execute(request);
    }

    public Response put(ApiRequest request) {
        return execute(request);
    }

    public Response patch(ApiRequest request) {
        return execute(request);
    }

    public Response delete(ApiRequest request) {
        return execute(request);
    }

    private Response execute(ApiRequest request) {

        RequestSpecification specification =
                RequestSpecificationFactory.create();

        applyRequest(specification, request);

        request.getAuthStrategy().apply(specification);

        return HttpExecutorRegistry
                .get(request.getMethod())
                .execute(specification, request.getEndpoint())
                .then()
                .spec(ResponseSpecificationFactory.create())
                .extract()
                .response();
    }

    private void applyRequest(RequestSpecification specification,
                              ApiRequest request) {

        if (!request.getHeaders().isEmpty()) {
            specification.headers(request.getHeaders());
        }

        if (!request.getQueryParams().isEmpty()) {
            specification.queryParams(request.getQueryParams());
        }

        if (!request.getPathParams().isEmpty()) {
            specification.pathParams(request.getPathParams());
        }

        if (request.getBody() != null) {
            specification.body(request.getBody());
        }
    }
}