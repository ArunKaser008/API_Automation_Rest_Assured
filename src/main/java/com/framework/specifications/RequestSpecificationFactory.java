package com.framework.specifications;

import com.framework.constants.ContentType;
import com.framework.context.FrameworkContext;
import com.framework.filters.RequestLoggingFilter;
import com.framework.filters.ResponseLoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecificationFactory {

    private RequestSpecificationFactory() {
    }

    public static RequestSpecification create() {

        return new RequestSpecBuilder()

                .setBaseUri(
                        FrameworkContext.getInstance()
                                .config()
                                .getBaseUrl())

                .setContentType(ContentType.JSON.getValue())

                .setAccept(ContentType.JSON.getValue())

                .addFilter(new RequestLoggingFilter())

                .addFilter(new ResponseLoggingFilter())

                .build();

    }

}