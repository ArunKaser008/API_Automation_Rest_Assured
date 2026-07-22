package com.framework.core.executor;

import com.framework.constants.HttpMethod;
import io.restassured.response.Response;

import java.util.EnumMap;
import java.util.Map;

/**
 * Registry of HTTP executors.
 */
public final class HttpExecutorRegistry {

    private static final Map<HttpMethod, HttpExecutor> EXECUTORS =
            new EnumMap<>(HttpMethod.class);

    static {

        EXECUTORS.put(
                HttpMethod.GET,
                (specification, endpoint) ->
                        specification.when().get(endpoint));

        EXECUTORS.put(
                HttpMethod.POST,
                (specification, endpoint) ->
                        specification.when().post(endpoint));

        EXECUTORS.put(
                HttpMethod.PUT,
                (specification, endpoint) ->
                        specification.when().put(endpoint));

        EXECUTORS.put(
                HttpMethod.PATCH,
                (specification, endpoint) ->
                        specification.when().patch(endpoint));

        EXECUTORS.put(
                HttpMethod.DELETE,
                (specification, endpoint) ->
                        specification.when().delete(endpoint));

    }

    private HttpExecutorRegistry() {
    }

    public static HttpExecutor get(HttpMethod method) {

        HttpExecutor executor = EXECUTORS.get(method);

        if (executor == null) {

            throw new IllegalArgumentException(
                    "Unsupported HTTP Method : " + method);

        }

        return executor;

    }

}