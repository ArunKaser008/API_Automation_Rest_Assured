package com.framework.config;

/**
 * Holds all configuration property keys used by the framework.
 *
 * Keeping property keys centralized avoids magic strings and
 * makes maintenance easier if property names change.
 */
public final class FrameworkConfig {

    private FrameworkConfig() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");
    }

    public static final String BASE_URL = "base.url";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String API_KEY = "api.key";

    public static final String CONNECTION_TIMEOUT = "connection.timeout";

    public static final String READ_TIMEOUT = "read.timeout";

}