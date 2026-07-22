package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Strategy interface for applying authentication
 * to an API request.
 */
@FunctionalInterface
public interface AuthStrategy {

    /**
     * Applies authentication to request specification.
     *
     * @param specification Rest Assured RequestSpecification
     */
    void apply(RequestSpecification specification);

}