package com.framework.core.executor;

import com.framework.models.ApiRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Strategy interface for executing HTTP requests.
 */
public interface HttpExecutor {

    Response execute(RequestSpecification requestSpecification,
                     ApiRequest apiRequest);

}