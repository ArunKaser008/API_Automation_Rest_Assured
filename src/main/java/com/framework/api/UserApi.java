package com.framework.api;

import io.restassured.response.Response;

/**
 * User API endpoints.
 */
public class UserApi extends BaseApi {

    /**
     * Returns users by page.
     *
     * @param page page number
     * @return Response
     */
    public Response getUsers(int page) {

        return apiClient.get("/users?page=" + page);

    }

}