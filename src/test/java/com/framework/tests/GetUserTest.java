package com.framework.tests;

import com.framework.client.BaseApiClient;
import com.framework.core.request.ApiRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest {

    private final BaseApiClient apiClient =
            new BaseApiClient();

    @Test
    public void shouldGetSingleUser() {

        ApiRequest request = ApiRequest.get("/api/users/2")
                .build();

        Response response = apiClient.get(request);

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(
                response.jsonPath().getInt("data.id"),
                2);

    }

}