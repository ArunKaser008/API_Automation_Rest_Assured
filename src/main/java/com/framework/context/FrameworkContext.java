package com.framework.context;

import com.framework.config.ConfigManager;

/**
 * Central framework context.
 */
public final class FrameworkContext {

    private static final FrameworkContext INSTANCE =
            new FrameworkContext();

    private final ConfigManager configManager;

    private FrameworkContext() {

        this.configManager = new ConfigManager();

    }

    public static FrameworkContext getInstance() {

        return INSTANCE;

    }

    public ConfigManager config() {

        return configManager;

    }

}