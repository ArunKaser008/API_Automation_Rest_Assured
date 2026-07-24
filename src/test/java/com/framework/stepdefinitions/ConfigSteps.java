package com.framework.stepdefinitions;

import com.framework.config.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.testng.Assert.*;

public class ConfigSteps {

    private ConfigManager configManager;

    @Given("the framework configuration is initialized")
    public void initializeConfiguration() {

        configManager = ConfigManager.getInstance();

    }

    @Then("the base URL should not be empty")
    public void verifyBaseUrl() {

        System.out.println((configManager.getBaseUrl()));
        assertNotNull(configManager.getBaseUrl());
        assertFalse(configManager.getBaseUrl().isBlank());

    }

    @Then("the connection timeout should be greater than {int}")
    public void verifyConnectionTimeout(int timeout) {

        System.out.println((configManager.getConnectionTimeout()));
        assertTrue(configManager.getConnectionTimeout() > timeout);

    }

    @Then("the read timeout should be greater than {int}")
    public void verifyReadTimeout(int timeout) {
        System.out.println(configManager.getReadTimeout() );
        assertTrue(configManager.getReadTimeout() > timeout);

    }

}