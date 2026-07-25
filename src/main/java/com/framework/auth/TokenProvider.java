package com.framework.auth;

/**
 * Contract for token providers.
 */
public interface TokenProvider {

    String getToken();

}