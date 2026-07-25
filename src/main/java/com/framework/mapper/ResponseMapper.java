package com.framework.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.qameta.allure.Allure;

import java.util.List;

/**
 * Utility class for mapping API responses
 * into Java objects.
 */
public final class ResponseMapper {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper();

        static {
            OBJECT_MAPPER.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
        }

        private ResponseMapper() {
            throw new UnsupportedOperationException(
                    "Utility class cannot be instantiated.");
        }

        /**
         * Maps API response to a single POJO.
         *
         * @param response Response
         * @param clazz Target class
     * @return mapped object
     */
    public static <T> T toObject(Response response, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(response.asString(), clazz);
        } catch (Exception exception) {
            // Attach raw response to Allure to help debugging mapping issues
            try {
                Allure.addAttachment("Response (mapping failure)", "text/plain", response.asString(), ".txt");
            } catch (Exception ignored) {
                // ignore
            }
            throw new RuntimeException(
                    "Unable to map response to object.",
                    exception);
        }
    }

    /**
     * Maps API response to a list of POJOs.
     *
     * @param response Response
     * @param clazz Target class
     * @return mapped list
     */
    public static <T> List<T> toList(Response response, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(
                    response.asString(),
                    OBJECT_MAPPER.getTypeFactory()
                            .constructCollectionType(List.class, clazz));
        } catch (Exception exception) {
            // Attach raw response to Allure to help debugging mapping issues
            try {
                Allure.addAttachment("Response (mapping failure)", "text/plain", response.asString(), ".txt");
            } catch (Exception ignored) {
                // ignore
            }
            throw new RuntimeException(
                    "Unable to map response.",
                    exception);
        }
    }
}