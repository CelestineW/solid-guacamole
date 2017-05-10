package ru.buepl.mobile.application.data.validation;

import lombok.Data;

/**
 * An error which occurs during validation.
 */
@Data
public final class ValidationError {
    /**
     * A message describing the error which took place.
     */
    private final String message;
}
