package com.framework.auth;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe token manager.
 */
public final class TokenManager {

    private static volatile String accessToken;

    private static volatile long expiryTime;

    private static final ReentrantLock LOCK =
            new ReentrantLock();

    private static TokenProvider tokenProvider;

    private TokenManager() {
    }

    public static void initialize(TokenProvider provider) {

        tokenProvider = provider;

    }

    public static String getToken() {

        if (isTokenValid()) {

            return accessToken;

        }

        LOCK.lock();

        try {

            if (!isTokenValid()) {

                refreshToken();

            }

            return accessToken;

        } finally {

            LOCK.unlock();

        }

    }

    private static boolean isTokenValid() {

        return accessToken != null &&
                System.currentTimeMillis() < expiryTime;

    }

    private static void refreshToken() {

        accessToken = tokenProvider.getToken();

        expiryTime =
                System.currentTimeMillis()
                        + (50 * 60 * 1000);

    }

    /**
     * Forces token refresh immediately (thread-safe).
     */
    public static void forceRefresh() {

        LOCK.lock();

        try {

            refreshToken();

        } finally {

            LOCK.unlock();

        }

    }

}