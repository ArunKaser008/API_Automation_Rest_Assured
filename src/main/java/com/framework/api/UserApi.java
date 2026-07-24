package com.framework.api;

import com.framework.models.ApiRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Business layer for User APIs.
 */
public class UserApi extends BaseApi {

    /**
     * Retrieve user by id.
     *
     * GET /users/{id}
     */
    public Response getUser(int id) {

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users/{id}")
                .pathParam("id", id)
                .build();

        return apiClient.get(request);

    }

    /**
     * Retrieve all users.
     *
     * GET /users
     */
    public Response getUsers() {

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users")
                .build();

        return apiClient.get(request);

    }

    /**
     * Retrieve comments by post id.
     *
     * GET /comments?postId=1
     */
    public Response getComments(int postId) {

        ApiRequest request = ApiRequest.builder()
                .endpoint("/comments")
                .queryParam("postId", postId)
                .build();

        return apiClient.get(request);

    }

    /**
     * Create new user.
     *
     * POST /users
     */
    public Response createUser() {

        Map<String, Object> body = new HashMap<>();

        body.put("name", "Automation User");
        body.put("username", "automation");
        body.put("email", "automation@test.com");

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users")
                .body(body)
                .build();

        return apiClient.post(request);

    }

    /**
     * Update complete user.
     *
     * PUT /users/{id}
     */
    public Response updateUser(int id) {

        Map<String, Object> body = new HashMap<>();

        body.put("id", id);
        body.put("name", "Automation User");
        body.put("username", "Automation User");
        body.put("email", "automation@test.com");

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users/{id}")
                .pathParam("id", id)
                .body(body)
                .build();

        return apiClient.put(request);

    }

    /**
     * Partial update.
     *
     * PATCH /users/{id}
     */
    public Response patchUser(int id) {

        Map<String, Object> body = new HashMap<>();

        body.put("username", "Updated User");

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users/{id}")
                .pathParam("id", id)
                .body(body)
                .build();

        return apiClient.patch(request);

    }

    /**
     * Delete user.
     *
     * DELETE /users/{id}
     */
    public Response deleteUser(int id) {

        ApiRequest request = ApiRequest.builder()
                .endpoint("/users/{id}")
                .pathParam("id", id)
                .build();

        return apiClient.delete(request);

    }

}