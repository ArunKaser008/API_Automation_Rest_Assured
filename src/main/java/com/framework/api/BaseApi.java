package com.framework.api;

import com.framework.client.ApiClient;

/**
 * Base class for all API classes.
 *
 * Provides access to the shared ApiClient.
 */
public abstract class BaseApi {

    protected final ApiClient apiClient;

    protected BaseApi() {

        this.apiClient = new ApiClient();

    }

}