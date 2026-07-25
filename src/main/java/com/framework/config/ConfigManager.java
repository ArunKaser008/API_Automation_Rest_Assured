package com.framework.config;

import java.util.Properties;

/**
 * Provides centralized and type-safe access to framework configuration.
 *
 * Configuration is loaded only once during framework initialization.
 */
public final class ConfigManager {

    private final Properties properties;

    /**
     * Private constructor to prevent external instantiation.
     */
    private ConfigManager() {

        String environmentName =
                System.getProperty("environment", "prod");

        Environment environment =
                Environment.from(environmentName);

        this.properties =
                ConfigurationLoader.load(environment);
    }

    /**
     * Holder class for lazy-loaded singleton instance.
     */
    private static class Holder {

        private static final ConfigManager INSTANCE =
                new ConfigManager();

    }

    /**
     * Returns the singleton instance.
     *
     * @return ConfigManager instance
     */
    public static ConfigManager getInstance() {

        return Holder.INSTANCE;

    }

    /**
     * Returns property value by key.
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
     * Returns integer property.
     *
     * @param key property key
     * @return integer value
     */
    private int getInt(String key) {

        try {

            return Integer.parseInt(get(key));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid integer value for property : " + key,
                    exception);

        }

    }

    /**
     * Returns boolean property.
     *
     * @param key property key
     * @return boolean value
     */
    private boolean getBoolean(String key) {

        return Boolean.parseBoolean(get(key));

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

        return getInt(FrameworkConfig.CONNECTION_TIMEOUT);

    }

    /**
     * Returns Read Timeout.
     */
    public int getReadTimeout() {

        return getInt(FrameworkConfig.READ_TIMEOUT);

    }

    public long getResponseTimeout() {

        try {

            return Long.parseLong(
                    get(FrameworkConfig.RESPONSE_TIMEOUT));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid response timeout.",
                    exception);

        }

    }

    /**
     * Returns whether retry is enabled.
     */
    public boolean isRetryEnabled() {

        return Boolean.parseBoolean(
                get(FrameworkConfig.RETRY_ENABLED));

    }

    /**
     * Returns maximum retry attempts.
     */
    public int getRetryMaxAttempts() {

        try {

            return Integer.parseInt(
                    get(FrameworkConfig.RETRY_MAX_ATTEMPTS));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid retry max attempts.",
                    exception);

        }

    }

    /**
     * Returns retry interval in milliseconds.
     */
    public long getRetryInterval() {

        try {

            return Long.parseLong(
                    get(FrameworkConfig.RETRY_INTERVAL));

        } catch (NumberFormatException exception) {

            throw new ConfigurationException(
                    "Invalid retry interval.",
                    exception);

        }

    }

}