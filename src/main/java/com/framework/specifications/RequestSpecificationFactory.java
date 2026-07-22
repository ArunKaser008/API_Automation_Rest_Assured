package com.framework.specifications;

import com.framework.context.FrameworkContext;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


/**
 * Creates RequestSpecification for API requests.
 */
public final class RequestSpecificationFactory {

    private RequestSpecificationFactory() {
    }

    /**
     * Creates default Request Specification.
     *
     * @return RequestSpecification
     */
    public static RequestSpecification create() {

        return new RequestSpecBuilder()

                .setBaseUri(
                        FrameworkContext.getInstance()
                                .config()
                                .getBaseUrl())

                .setContentType(ContentType.JSON)

                .setAccept(ContentType.JSON)

                .build();

    }

}