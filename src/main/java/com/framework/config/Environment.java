package com.framework.config;

/**
 * Supported framework environments.
 */
public enum Environment {

    QA("qa"),
    UAT("uat"),
    STAGE("stage"),
    PROD("prod");

    private final String value;

    Environment(String value) {
        this.value = value;
    }

    /**
     * Returns environment name.
     *
     * @return environment value
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns Environment enum from string.
     *
     * @param environment environment name
     * @return Environment
     */
    public static Environment from(String environment) {

        if (environment == null || environment.isBlank()) {
            return QA;
        }

        for (Environment env : values()) {

            if (env.value.equalsIgnoreCase(environment.trim())) {
                return env;
            }

        }

        throw new ConfigurationException(
                "Unsupported environment : "
                        + environment
                        + ". Supported environments are QA, UAT, STAGE and PROD."
        );
    }

}