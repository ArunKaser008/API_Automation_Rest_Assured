package com.framework.config;

/**
 * Exception thrown when framework configuration
 * cannot be loaded or validated.
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Creates a configuration exception with message.
     *
     * @param message Exception message
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Creates a configuration exception with
     * message and root cause.
     *
     * @param message Exception message
     * @param cause Root cause
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}