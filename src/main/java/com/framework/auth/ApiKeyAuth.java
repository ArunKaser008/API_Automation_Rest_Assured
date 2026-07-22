package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * API Key Authentication.
 */
public class ApiKeyAuth implements AuthStrategy {

    private final String headerName;
    private final String apiKey;

    public ApiKeyAuth(String headerName,
                      String apiKey) {

        this.headerName = headerName;
        this.apiKey = apiKey;

    }

    @Override
    public void apply(RequestSpecification specification) {

        specification.header(
                headerName,
                apiKey);

    }

}