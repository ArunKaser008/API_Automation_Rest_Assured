package com.framework.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class RestAssuredSmokeTest {

    @Test
    public void test() {

        RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .get("/users/1")
                .then()
                .statusCode(200);

    }
}