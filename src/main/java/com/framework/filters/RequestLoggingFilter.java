package com.framework.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {

        LOGGER.info("========== HTTP REQUEST ==========");
        LOGGER.info("Method : {}", requestSpec.getMethod());
        LOGGER.info("Headers:");
        requestSpec.getHeaders().forEach(header ->
                LOGGER.info("{} : {}", header.getName(), header.getValue()));

        if (requestSpec.getBody() != null) {
            LOGGER.info("Body:");
            LOGGER.info(requestSpec.getBody().toString());
        } else {
            LOGGER.info("Body: [No Body]");
        }

        LOGGER.info("====================================");

        return context.next(requestSpec, responseSpec);
    }
}