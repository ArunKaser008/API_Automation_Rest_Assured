package com.framework.stepdefinitions;

import com.framework.api.UserApi;
import com.framework.mapper.ResponseMapper;
import com.framework.models.response.UserResponse;
import com.framework.validator.ResponseValidator;
import com.framework.validator.SchemaValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class UserSteps {

    private final UserApi userApi = new UserApi();

    private Response response;
    private List<UserResponse> user;

    @Given("User API is available")
    public void userApiIsAvailable() {

        assertNotNull(userApi, "User API should be initialized.");

    }
    @When("I retrieve user with id {int}")
    public void retrieveUser(int id) {
        response = userApi.getUser(id);
        assertNotNull(response, "Response should not be null.");
        UserResponse userResponse = ResponseMapper.toObject(response, UserResponse.class);
        assertNotNull(userResponse, "UserResponse should not be null.");
        user = Collections.singletonList(userResponse); // Wrap the single object in a list
    }

    @When("I retrieve all users")
    public void retrieveAllUsers() {

        response = userApi.getUsers();

    }

    @When("I retrieve comments for post id {int}")
    public void retrieveComments(int postId) {

        response = userApi.getComments(postId);

    }

    @When("I create a new user")
    public void createUser() {

        response = userApi.createUser();

    }

    @When("I update user with id {int}")
    public void updateUser(int id) {

        response = userApi.updateUser(id);

    }

    @When("I update only username for user {int}")
    public void patchUser(int id) {

        response = userApi.patchUser(id);

    }

    @When("I delete user with id {int}")
    public void deleteUser(int id) {

        response = userApi.deleteUser(id);

    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {

        ResponseValidator.verifyStatusCode(
                response,
                statusCode);

    }

    @Then("the user id should be {int}")
    public void verifyUserId(int id) {
        ResponseValidator.verifyJsonPath(
                response,
                "id",
                id);
    }

    @Then("the username should be {string}")
    public void verifyUsername(String username) {
        ResponseValidator.verifyJsonPath(
                response,
                "username",
                username);
    }

    @Then("the email should contain {string}")
    public void verifyEmail(String value) {

        ResponseValidator.verifyJsonPathContains(
                response,
                "email",
                value);

    }

    @Then("the response should contain {int} users")
    public void verifyUserCount(int expectedUsers) {

        List<?> users =
                response.jsonPath().getList("");

        assertEquals(users.size(), expectedUsers);

    }

    @Then("every comment should belong to post id {int}")
    public void verifyComments(int postId) {

        List<Integer> postIds =
                response.jsonPath().getList("postId");

        assertTrue(
                postIds.stream()
                        .allMatch(id -> id == postId));

    }

    @Then("the response should contain generated id")
    public void verifyGeneratedId() {

        assertNotNull(
                response.jsonPath().get("id"));

    }

    @Then("the Content-Type should be {string}")
    public void verifyContentType(String contentType) {

        assertEquals(
                response.getContentType(),
                contentType);

    }

    @Then("response time should be less than {long} milliseconds")
    public void verifyResponseTime(long time) {

        assertTrue(
                response.getTime() < time);

    }

    @Then("response should match user schema")
    public void verifySchema() {

        SchemaValidator.validate(
                response,
                "user-schema.json");

    }

    @Then("the username should not be empty")
    public void verifyUsernameNotEmpty() {

        String username = response.jsonPath().get("username");
        assertTrue(username != null && !username.isBlank(),
                "Username should not be empty");

    }



}