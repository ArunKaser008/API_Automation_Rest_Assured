package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Bearer Token Authentication.
 */
public class BearerAuth implements AuthStrategy {

    private final String token;

    public BearerAuth(String token) {

        this.token = token;

    }

    @Override
    public void apply(RequestSpecification specification) {

        specification.header(
                "Authorization",
                "Bearer " + token);

    }

}