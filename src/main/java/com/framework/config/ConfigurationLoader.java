package com.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration properties based on the selected environment.
 */
public final class ConfigurationLoader {

    private ConfigurationLoader() {
    }

    /**
     * Loads properties file.
     *
     * @param environment selected environment
     * @return loaded properties
     */
    public static Properties load(Environment environment) {

        String fileName = environment.getValue() + ".properties";

        try (InputStream inputStream = ConfigurationLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new ConfigurationException(
                        "Configuration file not found : " + fileName);
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            validate(properties, fileName);

            return properties;

        } catch (IOException exception) {

            throw new ConfigurationException(
                    "Unable to load configuration file : " + fileName,
                    exception);

        }

    }

    /**
     * Validates mandatory configuration.
     */
    private static void validate(Properties properties,
                                 String fileName) {

        validateProperty(properties,
                FrameworkConfig.BASE_URL,
                fileName);

        validateProperty(properties,
                FrameworkConfig.CONNECTION_TIMEOUT,
                fileName);

        validateProperty(properties,
                FrameworkConfig.READ_TIMEOUT,
                fileName);

    }

    /**
     * Validates individual property.
     */
    private static void validateProperty(Properties properties,
                                         String key,
                                         String fileName) {

        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {

            throw new ConfigurationException(
                    "Missing property '"
                            + key
                            + "' in "
                            + fileName);

        }

    }

}