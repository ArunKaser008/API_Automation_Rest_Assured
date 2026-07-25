package com.framework.auth;

import com.framework.models.ApiRequest;
import io.restassured.specification.RequestSpecification;

public class BasicAuthStrategy
        implements AuthStrategy {

    @Override
    public void apply(RequestSpecification request,
                      ApiRequest apiRequest) {

        request.auth()
                .preemptive()
                .basic(
                        apiRequest.getUsername(),
                        apiRequest.getPassword());

    }

}