package ru.buepl.mobile.application.data.validation;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Something which can be validated.
 */
public interface Validatable {
    /**
     * Validates this object, returning a list of {@link ValidationError errors}
     * encountered. If this object is valid, then the list returned is empty.
     *
     * @param context the {@link Context} with which to perform the validation
     *                (needed to lookup resources).
     * @return a list of errors encountered during validation
     */
    @NonNull
    List<ValidationError> validate(@NonNull Context context);
}
