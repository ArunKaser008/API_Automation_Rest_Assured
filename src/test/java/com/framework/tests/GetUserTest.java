package com.framework.tests;

import com.framework.client.BaseApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest {

    private final BaseApiClient apiClient =
            new BaseApiClient();

    @Test
    public void shouldGetSingleUser() {

        Response response =
                apiClient.get("/api/users/2");

        Assert.assertEquals(
                response.statusCode(),
                200);

    }

}