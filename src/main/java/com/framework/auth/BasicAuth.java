package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * HTTP Basic Authentication.
 */
public class BasicAuth implements AuthStrategy {

    private final String username;
    private final String password;

    public BasicAuth(String username,
                     String password) {

        this.username = username;
        this.password = password;

    }

    @Override
    public void apply(RequestSpecification specification) {

        specification.auth()
                .preemptive()
                .basic(username, password);

    }

}