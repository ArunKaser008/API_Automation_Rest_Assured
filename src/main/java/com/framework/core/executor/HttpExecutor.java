package com.framework.core.executor;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Functional interface representing an HTTP executor.
 */
@FunctionalInterface
public interface HttpExecutor {

    Response execute(RequestSpecification specification,
                     String endpoint);

}