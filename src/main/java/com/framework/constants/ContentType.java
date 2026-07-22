package com.framework.constants;

/**
 * Supported request/response content types.
 */
public enum ContentType {

    JSON("application/json"),

    XML("application/xml"),

    FORM_URL_ENCODED("application/x-www-form-urlencoded"),

    MULTIPART("multipart/form-data"),

    TEXT("text/plain");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    /**
     * Returns MIME type.
     *
     * @return content type value
     */
    public String getValue() {
        return value;
    }

}