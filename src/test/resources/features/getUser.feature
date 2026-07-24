@UserAPI
Feature: User API Validation

    Background:
      Given User API is available

  @Smoke @GET
  Scenario: Retrieve user by id
    When I retrieve user with id 1
    Then the response status code should be 200
    And the user id should be 1
    And the username should be "Bret"
    And the email should contain "@"

  @Smoke @GET
  Scenario: Retrieve all users
    When I retrieve all users
    Then the response status code should be 200
    And the response should contain 10 users

  @GET
  Scenario: Retrieve comments using query parameter
    When I retrieve comments for post id 1
    Then the response status code should be 200
    And every comment should belong to post id 1

  @POST
  Scenario: Create a new user
    When I create a new user
    Then the response status code should be 201
    And the response should contain generated id

  @PUT
  Scenario: Update existing user
    When I update user with id 1
    Then the response status code should be 200
    And the username should be "Automation User"

  @PATCH
  Scenario: Partially update username
    When I update only username for user 1
    Then the response status code should be 200
    And the username should be "Updated User"

  @DELETE
  Scenario: Delete user
    When I delete user with id 1
    Then the response status code should be 200

  @Headers
  Scenario: Verify response headers
    When I retrieve user with id 1
    Then the Content-Type should be "application/json; charset=utf-8"

  @Performance
  Scenario: Verify response time
    When I retrieve user with id 1
    Then response time should be less than 5000 milliseconds