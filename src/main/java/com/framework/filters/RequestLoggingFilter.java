package com.framework.filters;

import com.framework.utils.FrameworkLogger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.response.Response;

/**
 * Logs outgoing HTTP requests.
 */
public class RequestLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {

        StringBuilder log = new StringBuilder();

        log.append("\n================ HTTP REQUEST ================\n");

        log.append("Method : ")
                .append(requestSpec.getMethod())
                .append('\n');

        log.append("URI    : ")
                .append(requestSpec.getURI())
                .append('\n');

        if (!requestSpec.getHeaders().asList().isEmpty()) {

            log.append("\nHeaders\n");

            requestSpec.getHeaders().forEach(header ->
                    log.append(header.getName())
                            .append(" : ")
                            .append(header.getValue())
                            .append('\n'));
        }

        if (!requestSpec.getQueryParams().isEmpty()) {

            log.append("\nQuery Parameters\n");

            requestSpec.getQueryParams()
                    .forEach((key, value) ->
                            log.append(key)
                                    .append(" = ")
                                    .append(value)
                                    .append('\n'));
        }

        if (!requestSpec.getPathParams().isEmpty()) {

            log.append("\nPath Parameters\n");

            requestSpec.getPathParams()
                    .forEach((key, value) ->
                            log.append(key)
                                    .append(" = ")
                                    .append(value)
                                    .append('\n'));
        }

        Object body = requestSpec.getBody();

        if (body != null) {

            log.append("\nBody\n");

            log.append(body).append('\n');
        }

        log.append("==============================================");

        FrameworkLogger.info(log.toString());

        return filterContext.next(requestSpec, responseSpec);
    }
}