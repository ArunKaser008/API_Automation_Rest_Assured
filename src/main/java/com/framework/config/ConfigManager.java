package com.framework.config;

import java.util.Properties;

/**
 * Provides type-safe access to framework configuration.
 */
public final class ConfigManager {

    private final Properties properties;

    /**
     * Creates ConfigManager and loads configuration
     * for the selected environment.
     */
    public ConfigManager() {

        String environmentName =
                System.getProperty("environment", "qa");

        Environment environment =
                Environment.from(environmentName);

        this.properties =
                ConfigurationLoader.load(environment);

    }

    /**
     * Returns property by key.
     *
     * @param key property key
     * @return property value
     */
    public String get(String key) {

        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {

            throw new ConfigurationException(
                    "Missing configuration property : " + key);

        }

        return value.trim();

    }

    /**
     * Returns Base URL.
     */
    public String getBaseUrl() {
        return get(FrameworkConfig.BASE_URL);
    }

    /**
     * Returns Username.
     */
    public String getUsername() {
        return get(FrameworkConfig.USERNAME);
    }

    /**
     * Returns Password.
     */
    public String getPassword() {
        return get(FrameworkConfig.PASSWORD);
    }

    /**
     * Returns API Key.
     */
    public String getApiKey() {
        return get(FrameworkConfig.API_KEY);
    }

    /**
     * Returns Connection Timeout.
     */
    public int getConnectionTimeout() {

        try {
            return Integer.parseInt(
                    get(FrameworkConfig.CONNECTION_TIMEOUT));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid connection timeout value.",
                    exception);

        }

    }

    /**
     * Returns Read Timeout.
     */
    public int getReadTimeout() {

        try {

            return Integer.parseInt(
                    get(FrameworkConfig.READ_TIMEOUT));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid read timeout value.",
                    exception);

        }

    }

}