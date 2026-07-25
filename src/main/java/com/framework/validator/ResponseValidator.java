package com.framework.validator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Objects;

/**
 * Utility class for validating API responses.
 */
public final class ResponseValidator {

    private ResponseValidator() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");
    }

    /**
     * Validates response status code.
     *
     * @param response API Response
     * @param expectedStatus Expected HTTP Status
     */
    public static void verifyStatusCode(Response response,
                                        int expectedStatus) {

        validateResponse(response);

        int actualStatus = response.getStatusCode();

        if (actualStatus != expectedStatus) {
            throw new AssertionError(
                    "Expected HTTP Status: "
                            + expectedStatus
                            + ", Actual: "
                            + actualStatus);
        }
    }

    /**
     * Validates response belongs to successful
     * HTTP status family (2xx).
     */
    public static void verifySuccessStatus(Response response) {

        validateResponse(response);

        int status = response.getStatusCode();

        if (status < 200 || status >= 300) {

            throw new AssertionError(
                    "Expected successful HTTP status (2xx), Actual: "
                            + status);

        }
    }

    /**
     * Validates response content type.
     */
    public static void verifyContentType(Response response,
                                         ContentType expectedContentType) {

        validateResponse(response);

        String actualContentType =
                response.getContentType();

        if (actualContentType == null ||
                !actualContentType.toLowerCase()
                        .contains(expectedContentType.name().toLowerCase())) {

            throw new AssertionError(
                    "Expected Content-Type: "
                            + expectedContentType
                            + ", Actual: "
                            + actualContentType);

        }

    }

    /**
     * Validates response time.
     */
    public static void verifyResponseTime(Response response,
                                          long maxResponseTime) {

        validateResponse(response);

        long actualTime = response.time();

        if (actualTime > maxResponseTime) {

            throw new AssertionError(
                    "Expected Response Time <= "
                            + maxResponseTime
                            + " ms, Actual: "
                            + actualTime
                            + " ms");

        }

    }

    /**
     * Validates response header value.
     */
    public static void verifyHeader(Response response,
                                    String headerName,
                                    String expectedValue) {

        validateResponse(response);

        String actualValue =
                response.getHeader(headerName);

        if (!Objects.equals(actualValue, expectedValue)) {

            throw new AssertionError(
                    "Header Validation Failed."
                            + "\nHeader : " + headerName
                            + "\nExpected : " + expectedValue
                            + "\nActual : " + actualValue);

        }

    }

    /**
     * Validates header exists.
     */
    public static void verifyHeaderExists(Response response,
                                          String headerName) {

        validateResponse(response);

        if (response.getHeader(headerName) == null) {

            throw new AssertionError(
                    "Header not found : "
                            + headerName);

        }

    }

    /**
     * Validates JSON field value.
     */
    public static void verifyJsonPath(Response response,
                                      String jsonPath,
                                      Object expectedValue) {

        validateResponse(response);

        Object actualValue =
                response.jsonPath().get(jsonPath);

        if (!Objects.equals(actualValue, expectedValue)) {

            throw new AssertionError(
                    "JSON Path Validation Failed."
                            + "\nPath : " + jsonPath
                            + "\nExpected : " + expectedValue
                            + "\nActual : " + actualValue);

        }

    }

    /**
     * Validates JSON path exists.
     */
    public static void verifyJsonPathExists(Response response,
                                            String jsonPath) {

        validateResponse(response);

        Object value =
                response.jsonPath().get(jsonPath);

        if (value == null) {

            throw new AssertionError(
                    "JSON Path not found : "
                            + jsonPath);

        }

    }

    /**
     * Validates response body contains text.
     */
    public static void verifyBodyContains(Response response,
                                          String expectedText) {

        validateResponse(response);

        String body = response.asString();

        if (!body.contains(expectedText)) {

            throw new AssertionError(
                    "Response body does not contain : "
                            + expectedText);

        }

    }

    /**
     * Validates response body is not empty.
     */
    public static void verifyBodyNotEmpty(Response response) {

        validateResponse(response);

        if (response.asString().isBlank()) {

            throw new AssertionError(
                    "Response body is empty.");

        }

    }

    /**
     * Validates response body is empty.
     */
    public static void verifyBodyEmpty(Response response) {

        validateResponse(response);

        if (!response.asString().isBlank()) {

            throw new AssertionError(
                    "Expected empty response body.");

        }

    }

    /**
     * Validates response is not null.
     */
    private static void validateResponse(Response response) {

        if (response == null) {

            throw new AssertionError(
                    "API Response cannot be null.");

        }

    }

}