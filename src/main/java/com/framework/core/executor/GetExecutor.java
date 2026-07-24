package com.framework.core.executor;

import com.framework.models.ApiRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Executes HTTP GET requests.
 */
public class GetExecutor extends BaseExecutor {

    @Override
    public Response execute(RequestSpecification requestSpecification,
                            ApiRequest apiRequest) {


        RequestSpecification request = prepareRequest(requestSpecification, apiRequest);


        return request
                .when()
                .get(apiRequest.getEndpoint());

    }

}