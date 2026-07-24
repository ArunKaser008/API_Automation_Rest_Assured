package com.framework.client;

import com.framework.config.ConfigManager;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

/**
 * Factory class responsible for creating reusable
 * Response Specifications.
 *
 * Every API response will automatically be validated
 * against this specification.
 */
public final class ResponseSpecificationFactory {

    /**
     * Maximum acceptable response time.
     * Later this can come from properties.
     */

    private static final ConfigManager CONFIG =
            ConfigManager.getInstance();
    private ResponseSpecificationFactory() {

        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");

    }

    /**
     * Creates the default response specification.
     *
     * @return ResponseSpecification
     */
    public static ResponseSpecification createDefault() {

        return new ResponseSpecBuilder()

                // Response must be JSON
                .expectContentType(ContentType.JSON)

                // Response should arrive within configured time
                .expectResponseTime(
                        Matchers.lessThan(CONFIG.getResponseTimeout()),
                        TimeUnit.MILLISECONDS)

                .build();

    }

}