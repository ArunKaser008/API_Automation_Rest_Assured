package com.framework.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {

        Response response = context.next(requestSpec, responseSpec);

        LOGGER.info("========== HTTP RESPONSE ==========");
        LOGGER.info("Response Time : {} ms", response.time());

        LOGGER.info("Headers:");
        response.getHeaders().forEach(header ->
                LOGGER.info("{} : {}", header.getName(), header.getValue()));

        LOGGER.info("Body:");
        LOGGER.info(response.asPrettyString());

        LOGGER.info("====================================");

        return response;
    }
}