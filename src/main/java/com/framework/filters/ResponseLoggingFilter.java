package com.framework.filters;

import com.framework.utils.FrameworkLogger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.response.Response;

/**
 * Logs incoming HTTP responses.
 */
public class ResponseLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {

        Response response =
                filterContext.next(requestSpec, responseSpec);

        StringBuilder log = new StringBuilder();

        log.append("\n================ HTTP RESPONSE ================\n");

        log.append("Status Code : ")
                .append(response.getStatusCode())
                .append('\n');

        log.append("Status Line : ")
                .append(response.getStatusLine())
                .append('\n');

        log.append("Response Time : ")
                .append(response.time())
                .append(" ms\n");

        log.append("\nHeaders\n");

        response.getHeaders().forEach(header ->
                log.append(header.getName())
                        .append(" : ")
                        .append(header.getValue())
                        .append('\n'));

        log.append("\nBody\n");

        log.append(response.asPrettyString()).append('\n');

        log.append("==============================================");

        FrameworkLogger.info(log.toString());

        return response;
    }
}