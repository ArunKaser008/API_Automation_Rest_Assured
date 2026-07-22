package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Represents a request with no authentication.
 */
public class NoAuth implements AuthStrategy {

    @Override
    public void apply(RequestSpecification specification) {
        // No Authentication
    }

}