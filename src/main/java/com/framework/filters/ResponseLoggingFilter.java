package com.framework.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Allure;

public class ResponseLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {

        Response response = context.next(requestSpec, responseSpec);

        StringBuilder sb = new StringBuilder();

        LOGGER.info("========== HTTP RESPONSE ==========");
        LOGGER.info("Response Time : {} ms", response.time());
        sb.append("Response Time: ").append(response.time()).append(" ms\n");

        LOGGER.info("Status Code : {}", response.getStatusCode());
        sb.append("Status Code: ").append(response.getStatusCode()).append('\n');

        LOGGER.info("Headers:");
        sb.append("Headers:\n");
        response.getHeaders().forEach(header -> {
            LOGGER.info("{} : {}", header.getName(), header.getValue());
            sb.append(header.getName()).append(": ").append(header.getValue()).append('\n');
        });

        LOGGER.info("Body:");
        LOGGER.info(response.asPrettyString());
        sb.append("Body:\n").append(response.asPrettyString()).append('\n');

        LOGGER.info("====================================");

        // Attach response to Allure report
        try {
            Allure.addAttachment("HTTP Response", "text/plain", sb.toString(), ".txt");
        } catch (Exception ignored) {
            // Best effort - do not fail tests if Allure not present
        }

        return response;
    }
}