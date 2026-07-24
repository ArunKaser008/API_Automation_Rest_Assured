package com.framework.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an API request.
 */
public class ApiRequest {

    private String endpoint;

    private Object body;

    private final Map<String, String> headers = new HashMap<>();

    private final Map<String, Object> queryParams = new HashMap<>();

    private final Map<String, Object> pathParams = new HashMap<>();

    private ApiRequest() {
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Object getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final ApiRequest request = new ApiRequest();

        public Builder endpoint(String endpoint) {

            request.endpoint = endpoint;

            return this;

        }

        public Builder body(Object body) {

            request.body = body;

            return this;

        }

        public Builder header(String key,
                              String value) {

            request.headers.put(key, value);

            return this;

        }

        public Builder queryParam(String key,
                                  Object value) {

            request.queryParams.put(key, value);

            return this;

        }

        public Builder pathParam(String key,
                                 Object value) {

            request.pathParams.put(key, value);

            return this;

        }

        public ApiRequest build() {

            return request;

        }

    }

}