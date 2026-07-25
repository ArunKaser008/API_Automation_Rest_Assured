package com.framework.auth;

import com.framework.models.ApiRequest;
import io.restassured.specification.RequestSpecification;

public class NoAuthStrategy
        implements AuthStrategy {

    @Override
    public void apply(RequestSpecification request,
                      ApiRequest apiRequest) {

        // No Authentication

    }

}