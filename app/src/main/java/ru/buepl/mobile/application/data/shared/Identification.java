package ru.buepl.mobile.application.data.shared;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import ru.buepl.mobile.application.R;
import ru.buepl.mobile.application.data.validation.Validatable;
import ru.buepl.mobile.application.data.validation.ValidationError;

import static ru.buepl.mobile.application.data.validation.Validation.*;

@Data
@Builder(builderClassName = "Builder")
public final class Identification implements Validatable {
    private String birthDate;
    private String placeOfBirth;
    private Gender gender;
    private String citizenship;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(birthDate, R.string.date_of_birth, context),
                nonEmptyString(placeOfBirth, R.string.place_of_birth, context),
                nonNull(gender, R.string.gender, context),
                nonEmptyString(citizenship, R.string.citizenship, context)
        );
    }

    /* Let it be known that I object to only having two options for gender */
    public enum Gender {
        MALE, FEMALE
    }
}
