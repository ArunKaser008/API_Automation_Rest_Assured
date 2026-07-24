package com.framework.stepdefinitions;

import com.framework.api.UserApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.testng.Assert.*;

public class UserSteps {

    UserApi userApi = new UserApi();

    private Response response;

    @Given("User API is initialized")
    public void initializeUserApi() {

        userApi = new UserApi();

        assertNotNull(userApi, "User API should be initialized.");

    }

    @When("I request page {int} users")
    public void requestUsers(int page) {

        response = userApi.getUsers(page);

    }

    @Then("response status should be {int}")
    public void verifyStatus(int status) {

        assertEquals(response.statusCode(), status);

    }

    @Then("total users should be greater than {int}")
    public void verifyUsers(int total) {

        int users =
                response.jsonPath().getList("data").size();

        assertTrue(users > total);

    }

}