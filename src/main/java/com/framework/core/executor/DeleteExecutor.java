package com.framework.core.executor;

import com.framework.models.ApiRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Executes HTTP DELETE requests.
 */
public class DeleteExecutor extends BaseExecutor {

    @Override
    public Response execute(RequestSpecification requestSpecification,
                            ApiRequest apiRequest) {

        RequestSpecification request = prepareRequest(requestSpecification, apiRequest);


        return request
                .when()
                .delete(apiRequest.getEndpoint());

    }

}