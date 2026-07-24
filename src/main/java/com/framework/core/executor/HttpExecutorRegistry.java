package com.framework.core.executor;

import com.framework.client.HttpMethod;

import java.util.EnumMap;
import java.util.Map;

/**
 * Registry for HTTP Executors.
 */
public final class HttpExecutorRegistry {

    private static final Map<HttpMethod, HttpExecutor> EXECUTORS =
            new EnumMap<>(HttpMethod.class);

    static {

        EXECUTORS.put(HttpMethod.GET, new GetExecutor());
        EXECUTORS.put(HttpMethod.POST, new PostExecutor());
        EXECUTORS.put(HttpMethod.PUT, new PutExecutor());
        EXECUTORS.put(HttpMethod.PATCH, new PatchExecutor());
        EXECUTORS.put(HttpMethod.DELETE, new DeleteExecutor());

    }

    private HttpExecutorRegistry() {

        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");

    }

    public static HttpExecutor get(HttpMethod httpMethod) {

        HttpExecutor executor = EXECUTORS.get(httpMethod);

        if (executor == null) {

            throw new IllegalArgumentException(
                    "No executor registered for HTTP Method : "
                            + httpMethod);

        }

        return executor;

    }

}