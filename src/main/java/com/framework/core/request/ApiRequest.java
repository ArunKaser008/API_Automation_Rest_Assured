package com.framework.core.request;

import com.framework.auth.AuthStrategy;
import com.framework.auth.NoAuth;
import com.framework.constants.HttpMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApiRequest {

    private final HttpMethod method;
    private final String endpoint;
    private final Map<String, String> headers;
    private final Map<String, Object> queryParams;
    private final Map<String, Object> pathParams;
    private final Object body;
    private final AuthStrategy authStrategy;

    private ApiRequest(Builder builder) {

        this.method = builder.method;
        this.endpoint = builder.endpoint;
        this.headers = Collections.unmodifiableMap(builder.headers);
        this.queryParams = Collections.unmodifiableMap(builder.queryParams);
        this.pathParams = Collections.unmodifiableMap(builder.pathParams);
        this.body = builder.body;
        this.authStrategy = builder.authStrategy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder get(String endpoint) {
        return new Builder()
                .method(HttpMethod.GET)
                .endpoint(endpoint);
    }

    public static Builder post(String endpoint) {
        return new Builder()
                .method(HttpMethod.POST)
                .endpoint(endpoint);
    }

    public static Builder put(String endpoint) {
        return new Builder()
                .method(HttpMethod.PUT)
                .endpoint(endpoint);
    }

    public static Builder patch(String endpoint) {
        return new Builder()
                .method(HttpMethod.PATCH)
                .endpoint(endpoint);
    }

    public static Builder delete(String endpoint) {
        return new Builder()
                .method(HttpMethod.DELETE)
                .endpoint(endpoint);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
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

    public Object getBody() {
        return body;
    }

    public AuthStrategy getAuthStrategy() {
        return authStrategy;
    }

    public static final class Builder {

        private HttpMethod method;
        private String endpoint;

        private final Map<String, String> headers = new HashMap<>();
        private final Map<String, Object> queryParams = new HashMap<>();
        private final Map<String, Object> pathParams = new HashMap<>();

        private Object body;

        private AuthStrategy authStrategy = new NoAuth();

        private Builder() {
        }

        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder header(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder queryParam(String key, Object value) {
            queryParams.put(key, value);
            return this;
        }

        public Builder queryParams(Map<String, Object> params) {
            this.queryParams.putAll(params);
            return this;
        }

        public Builder pathParam(String key, Object value) {
            pathParams.put(key, value);
            return this;
        }

        public Builder pathParams(Map<String, Object> params) {
            this.pathParams.putAll(params);
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public Builder auth(AuthStrategy authStrategy) {
            this.authStrategy = authStrategy;
            return this;
        }

        public ApiRequest build() {

            if (method == null) {
                throw new IllegalStateException("HTTP Method is required.");
            }

            if (endpoint == null || endpoint.isBlank()) {
                throw new IllegalStateException("Endpoint is required.");
            }

            return new ApiRequest(this);
        }
    }
}