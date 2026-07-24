package com.framework.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Generic HTTP client responsible for executing REST requests.
 *
 * This class hides Rest Assured implementation from the
 * business API layer.
 */
public class ApiClient {

    private final RequestSpecification requestSpecification;

    /**
     * Creates ApiClient with default request specification.
     */
    public ApiClient() {

        this.requestSpecification =
                RequestSpecificationFactory.getDefaultRequestSpecification();

    }

    /**
     * Creates ApiClient with custom request specification.
     *
     * Useful for authenticated requests or multipart requests.
     */
    public ApiClient(RequestSpecification requestSpecification) {

        this.requestSpecification = requestSpecification;

    }

    /**
     * Executes HTTP GET request.
     *
     * @param endpoint API endpoint
     * @return Response
     */
    public Response get(String endpoint) {

        return RestAssured
                .given(requestSpecification)
                .when()
                .get(endpoint);

    }

    /**
     * Executes HTTP POST request.
     *
     * @param endpoint API endpoint
     * @param body Request payload
     * @return Response
     */
    public Response post(String endpoint,
                         Object body) {

        return RestAssured
                .given(requestSpecification)
                .body(body)
                .when()
                .post(endpoint);

    }

    /**
     * Executes HTTP PUT request.
     */
    public Response put(String endpoint,
                        Object body) {

        return RestAssured
                .given(requestSpecification)
                .body(body)
                .when()
                .put(endpoint);

    }

    /**
     * Executes HTTP PATCH request.
     */
    public Response patch(String endpoint,
                          Object body) {

        return RestAssured
                .given(requestSpecification)
                .body(body)
                .when()
                .patch(endpoint);

    }

    /**
     * Executes HTTP DELETE request.
     */
    public Response delete(String endpoint) {

        return RestAssured
                .given(requestSpecification)
                .when()
                .delete(endpoint);

    }

}