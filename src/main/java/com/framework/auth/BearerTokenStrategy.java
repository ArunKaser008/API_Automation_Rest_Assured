package com.framework.auth;

import com.framework.models.ApiRequest;
import io.restassured.specification.RequestSpecification;

public class BearerTokenStrategy
        implements AuthStrategy {

    @Override
    public void apply(RequestSpecification request,
                      ApiRequest apiRequest) {

        request.auth()
                .oauth2(TokenManager.getToken());
    }

}