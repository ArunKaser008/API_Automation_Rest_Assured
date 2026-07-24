package com.framework.core.executor;

import com.framework.models.ApiRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Executes HTTP POST requests.
 */
public class PostExecutor extends BaseExecutor {

    @Override
    public Response execute(RequestSpecification requestSpecification,
                            ApiRequest apiRequest) {

        RequestSpecification request = prepareRequest(requestSpecification, apiRequest);

        return request
                .when()
                .post(apiRequest.getEndpoint());
    }
}