package com.framework.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Allure;

public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {

        StringBuilder sb = new StringBuilder();

        LOGGER.info("========== HTTP REQUEST ==========");
        LOGGER.info("Method : {}", requestSpec.getMethod());
        sb.append("Method: ").append(requestSpec.getMethod()).append('\n');

        LOGGER.info("URI : {}", requestSpec.getURI());
        sb.append("URI: ").append(requestSpec.getURI()).append('\n');

        LOGGER.info("Headers:");
        sb.append("Headers:\n");
        requestSpec.getHeaders().forEach(header -> {
            LOGGER.info("{} : {}", header.getName(), header.getValue());
            sb.append(header.getName()).append(": ").append(header.getValue()).append('\n');
        });

        if (requestSpec.getBody() != null) {
            LOGGER.info("Body:");
            LOGGER.info(requestSpec.getBody().toString());
            sb.append("Body:\n").append(requestSpec.getBody().toString()).append('\n');
        } else {
            LOGGER.info("Body: [No Body]");
            sb.append("Body: [No Body]\n");
        }

        LOGGER.info("====================================");

        // Attach request details to Allure report
        try {
            Allure.addAttachment("HTTP Request", "text/plain", sb.toString(), ".txt");
        } catch (Exception ignored) {
            // Best effort - do not fail tests if Allure not present
        }

        return context.next(requestSpec, responseSpec);
    }
}