package com.framework.auth;

import java.util.EnumMap;
import java.util.Map;

public final class AuthStrategyFactory {

    private static final Map<AuthType, AuthStrategy> STRATEGIES =
            new EnumMap<>(AuthType.class);

    static {

        STRATEGIES.put(
                AuthType.NONE,
                new NoAuthStrategy());

        STRATEGIES.put(
                AuthType.BASIC,
                new BasicAuthStrategy());

        STRATEGIES.put(
                AuthType.BEARER,
                new BearerTokenStrategy());

        STRATEGIES.put(
                AuthType.API_KEY,
                new ApiKeyStrategy());

    }

    private AuthStrategyFactory() {
    }

    public static AuthStrategy get(AuthType authType) {

        return STRATEGIES.getOrDefault(
                authType,
                new NoAuthStrategy());

    }

}