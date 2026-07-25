package com.framework.models;

import com.framework.auth.AuthType;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an API request.
 */
public final class ApiRequest {

    private final String endpoint;

    private final Object body;

    private final Map<String, String> headers;

    private final Map<String, Object> queryParams;

    private final Map<String, Object> pathParams;

    /* Authentication */

    private final AuthType authType;

    private final String username;

    private final String password;

    private final String bearerToken;

    private final String apiKey;

    private final String apiKeyHeader;

    private ApiRequest(Builder builder) {

        this.endpoint = builder.endpoint;

        this.body = builder.body;

        this.headers = builder.headers;

        this.queryParams = builder.queryParams;

        this.pathParams = builder.pathParams;

        this.authType = builder.authType;

        this.username = builder.username;

        this.password = builder.password;

        this.bearerToken = builder.bearerToken;

        this.apiKey = builder.apiKey;

        this.apiKeyHeader = builder.apiKeyHeader;

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

    public AuthType getAuthType() {
        return authType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiKeyHeader() {
        return apiKeyHeader;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder
     */
    public static final class Builder {

        private String endpoint;

        private Object body;

        private final Map<String, String> headers = new HashMap<>();

        private final Map<String, Object> queryParams = new HashMap<>();

        private final Map<String, Object> pathParams = new HashMap<>();

        /* Authentication */

        private AuthType authType = AuthType.NONE;

        private String username;

        private String password;

        private String bearerToken;

        private String apiKey;

        private String apiKeyHeader = "x-api-key";

        public Builder endpoint(String endpoint) {

            this.endpoint = endpoint;

            return this;

        }

        public Builder body(Object body) {

            this.body = body;

            return this;

        }

        public Builder header(String key,
                              String value) {

            headers.put(key, value);

            return this;

        }

        public Builder headers(Map<String, String> headers) {

            this.headers.putAll(headers);

            return this;

        }

        public Builder queryParam(String key,
                                  Object value) {

            queryParams.put(key, value);

            return this;

        }

        public Builder pathParam(String key,
                                 Object value) {

            pathParams.put(key, value);

            return this;

        }

        public Builder authType(AuthType authType) {

            this.authType = authType;

            return this;

        }

        public Builder username(String username) {

            this.username = username;

            return this;

        }

        public Builder password(String password) {

            this.password = password;

            return this;

        }

        public Builder bearerToken(String bearerToken) {

            this.bearerToken = bearerToken;

            return this;

        }

        public Builder apiKey(String apiKey) {

            this.apiKey = apiKey;

            return this;

        }

        public Builder apiKeyHeader(String apiKeyHeader) {

            this.apiKeyHeader = apiKeyHeader;

            return this;

        }

        public ApiRequest build() {

            if (endpoint == null || endpoint.isBlank()) {

                throw new IllegalArgumentException(
                        "Endpoint cannot be null or blank.");

            }

            return new ApiRequest(this);

        }

    }

}