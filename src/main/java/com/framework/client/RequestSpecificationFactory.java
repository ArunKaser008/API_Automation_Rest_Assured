package com.framework.client;

import com.framework.config.ConfigManager;
import com.framework.filters.RequestLoggingFilter;
import com.framework.filters.ResponseLoggingFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecificationFactory {

    private static final ConfigManager CONFIG =
            ConfigManager.getInstance();

    private RequestSpecificationFactory() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");
    }

    /**
     * Creates the default Request Specification.
     *
     * @return RequestSpecification
     */
    public static RequestSpecification getDefaultRequestSpecification() {

        RestAssuredConfig config =
                RestAssuredConfig.config()
                        .httpClient(
                                HttpClientConfig.httpClientConfig()
                                        .setParam(
                                                "http.connection.timeout",
                                                CONFIG.getConnectionTimeout())
                                        .setParam(
                                                "http.socket.timeout",
                                                CONFIG.getReadTimeout()));

        return new RequestSpecBuilder()


                .setBaseUri(CONFIG.getBaseUrl())

                .setContentType(ContentType.JSON)

                .setAccept(ContentType.JSON)

                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())

                // Allure filter will automatically attach requests/responses to Allure report
                .addFilter(new AllureRestAssured())

                .setRelaxedHTTPSValidation()

                .setConfig(config)

                .build();

    }

    }