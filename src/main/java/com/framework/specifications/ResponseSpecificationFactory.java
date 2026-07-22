package com.framework.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

/**
 * Creates reusable ResponseSpecification objects.
 */
public final class ResponseSpecificationFactory {

    private ResponseSpecificationFactory() {
    }

    /**
     * Default response specification.
     */
    public static ResponseSpecification create() {

        return new ResponseSpecBuilder()

                .expectContentType(ContentType.JSON)

                .expectResponseTime(
                        org.hamcrest.Matchers.lessThan(5000L),
                        TimeUnit.MILLISECONDS)

                .build();

    }

}