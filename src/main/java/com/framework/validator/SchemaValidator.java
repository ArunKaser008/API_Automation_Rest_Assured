package com.framework.validator;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;

/**
 * Validates JSON responses against schema files.
 */
public final class SchemaValidator {

    private SchemaValidator() {

        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");

    }

    /**
     * Validates response against JSON schema.
     *
     * @param response API Response
     * @param schemaFile schema file under resources/schemas
     */
    public static void validate(Response response,
                                String schemaFile) {

        if (response == null) {

            throw new AssertionError(
                    "Response cannot be null.");

        }

        InputStream inputStream =
                SchemaValidator.class
                        .getClassLoader()
                        .getResourceAsStream("schemas/" + schemaFile);

        if (inputStream == null) {

            throw new AssertionError(
                    "Schema file not found : "
                            + schemaFile);

        }

        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(inputStream));

    }

}