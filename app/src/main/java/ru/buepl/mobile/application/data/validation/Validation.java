package ru.buepl.mobile.application.data.validation;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import ru.buepl.mobile.application.R;

/**
 * Utility class for validating objects.
 *
 * @see Validatable
 */
public final class Validation {
    private Validation() {}

    /**
     * Aggregates the specified lists of {@link ValidationError}s into a single list.
     *
     * @param validationErrorLists the lists to aggregate
     * @return a single list containing all ValidationErrors passed in
     */
    @SafeVarargs
    public static List<ValidationError> aggregateErrors(@NonNull List<ValidationError>... validationErrorLists) {
        List<ValidationError> res = new ArrayList<>();
        for (List<ValidationError> errors : validationErrorLists) {
            res.addAll(errors);
        }
        return res;
    }

    /**
     * Map {@link ValidationError errors} from a section to have a prefix describing
     * the section in which the occurred.
     *
     * @param validatable             members of the section to validate
     * @param sectionStringResourceId the ID of {@link R.string string resource} naming
     *                                the section which is being validated
     * @param context                 the {@link Context} with which to perform the validation
     *                                (needed to lookup string resources)
     * @return a list containing the validation errors for the section
     */
    public static List<ValidationError> mapErrorsForSection(@NonNull Validatable validatable,
                                                            int sectionStringResourceId,
                                                            @NonNull Context context) {
        return mapErrorsForSection(validatable.validate(context), sectionStringResourceId, context);
    }

    /**
     * Map {@link ValidationError errors} from a section to have a prefix describing
     * the section in which the occurred.
     *
     * @param validationErrors        errors encountered when validating fields in the
     *                                section
     * @param sectionStringResourceId the ID of {@link R.string string resource} naming
     *                                the section which is being validated
     * @param context                 the {@link Context} with which to perform the validation
     *                                (needed to lookup string resources)
     * @return a list containing the validation errors for the section
     */
    public static List<ValidationError> mapErrorsForSection(@NonNull List<ValidationError> validationErrors,
                                                            int sectionStringResourceId,
                                                            @NonNull Context context) {
        List<ValidationError> res = new ArrayList<>();
        Resources resources = context.getResources();
        String separator = resources.getString(R.string.validation_separator);

        for (ValidationError error: validationErrors) {
            String message = resources.getString(R.string.incomplete_section)
                    + resources.getString(sectionStringResourceId)
                    + " " + separator + " "
                    + error.getMessage();
            res.add(new ValidationError(message));
        }
        return res;
    }

    /**
     * Validates the specified {@link Validatable} objects, and
     * returns a list containing all {@link ValidationError errors}
     * encountered during validation.
     *
     * <p>Note: The original sources of the {@link ValidationError}s will
     * not be recorded.
     *
     * @param context    the {@link Context} with which to perform the validation
     *                   (needed to lookup resources)
     * @param toValidate the objects to validate
     * @return a list containing all errors encountered during validation
     */
    public static List<ValidationError> validateAll(@NonNull Context context,
                                                    @NonNull Validatable... toValidate) {
        List<ValidationError> res = new ArrayList<>();
        for (Validatable v: toValidate) {
            res.addAll(v.validate(context));
        }
        return res;
    }

    /**
     * Validates that an object is not {@code null}.
     *
     * @param obj                the object to validate
     * @param stringResourceId the ID of {@link R.string string resource} naming the field
     *                         which is being validated
     * @param context          the {@link Context} with which to perform the validation
     *                         (needed to lookup string resources)
     * @return a list containing a {@link ValidationError} if the object is null;
     * an empty list otherwise.
     */
    public static List<ValidationError> nonNull(@Nullable Object obj,
                                                int stringResourceId,
                                                @NonNull Context context) {
        if (obj == null) {
            return missingField(stringResourceId, context);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Validates a {@link Validatable validatable} object, but only
     * if it is not null.
     *
     * @param validatable the possibly-null object to validate
     * @param context     the {@link Context} with which to perform the validation
     *                    (needed to lookup string resources)
     * @return a list containing errors encountered during validation
     */
    public static List<ValidationError> nullOrValid(@Nullable Validatable validatable,
                                                    @NonNull Context context) {
        if (validatable == null) {
            return Collections.emptyList();
        } else {
            return validatable.validate(context);
        }
    }

    /**
     * Validates that a string is not {@code null} or empty.
     *
     * @param toValidate       the string to validate.
     * @param stringResourceId the ID of {@link R.string string resource} naming the field
     *                         which is being validated
     * @param context          the {@link Context} with which to perform the validation
     *                         (needed to lookup string resources)
     * @return a list containing a {@link ValidationError} if the string is null or empty;
     * an empty list otherwise.
     */
    public static List<ValidationError> nonEmptyString(@Nullable String toValidate,
                                                       int stringResourceId,
                                                       @NonNull Context context) {
        if (emptyOrNull(toValidate)) {
            return missingField(stringResourceId, context);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Validates that a string is not {@code null} or empty, and matches a specified pattern.

     * @param toValidate       the string to validate.
     * @param pattern          the {@link Pattern} the string should match
     * @param stringResourceId the ID of {@link R.string string resource} naming the field
     *                         which is being validated
     * @param context          the {@link Context} with which to perform the validation
     *                         (needed to lookup string resources)
     * @return a list containing a {@link ValidationError} if the string is null, empty, or
     * does not match the specified pattern; an empty list otherwise
     */
    public static List<ValidationError> nonEmptyStringMatching(@Nullable String toValidate,
                                                               Pattern pattern,
                                                               int stringResourceId,
                                                               @NonNull Context context) {
        if (emptyOrNull(toValidate)) {
            return missingField(stringResourceId, context);
        } else if (!pattern.matcher(toValidate).matches()) {
            return invalidField(stringResourceId, context);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Validates that a string is {@code null}, empty, or matches a specified pattern.

     * @param toValidate       the string to validate.
     * @param pattern          the {@link Pattern} the string should match
     * @param stringResourceId the ID of {@link R.string string resource} naming the field
     *                         which is being validated
     * @param context          the {@link Context} with which to perform the validation
     *                         (needed to lookup string resources)
     * @return a list containing a {@link ValidationError} if the string not null or empty,
     * and matches the specified pattern; an empty list otherwise
     */
    private static List<ValidationError> emptyStringOrMatching(@Nullable String toValidate,
                                                               Pattern pattern,
                                                               int stringResourceId,
                                                               @NonNull Context context) {
        if (!emptyOrNull(toValidate) && !pattern.matcher(toValidate).matches()) {
            return invalidField(stringResourceId, context);
        } else {
            return Collections.emptyList();
        }
    }

    private static boolean emptyOrNull(@Nullable String s) {
        return s == null || s.isEmpty();
    }

    private static List<ValidationError> missingField(int stringResourceId, Context context) {
        Resources resources = context.getResources();
        String message = resources.getString(R.string.missing_field) + resources.getString(stringResourceId);
        return Collections.singletonList(new ValidationError(message));
    }

    private static List<ValidationError> invalidField(int stringResourceId, Context context) {
        Resources resources = context.getResources();
        String message = resources.getString(R.string.invalid_field) + resources.getString(stringResourceId);
        return Collections.singletonList(new ValidationError(message));
    }
}
