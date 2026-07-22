package com.framework.client;

import com.framework.specifications.RequestSpecificationFactory;
import com.framework.specifications.ResponseSpecificationFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Base client for executing HTTP requests.
 */
public class BaseApiClient {

    /**
     * Executes GET request.
     */
    public Response get(String endpoint) {

        return given()

                .spec(RequestSpecificationFactory.create())

                .when()

                .get(endpoint)

                .then()

                .spec(ResponseSpecificationFactory.create())

                .extract()

                .response();

    }

}