package com.framework.auth;

import com.framework.models.ApiRequest;
import io.restassured.specification.RequestSpecification;

public class ApiKeyStrategy
        implements AuthStrategy {

    @Override
    public void apply(RequestSpecification request,
                      ApiRequest apiRequest) {

        request.header(
                apiRequest.getApiKeyHeader(),
                apiRequest.getApiKey());

    }

}