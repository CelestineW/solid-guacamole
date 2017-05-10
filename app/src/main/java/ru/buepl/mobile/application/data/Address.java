package ru.buepl.mobile.application.data;

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
public final class Address implements Validatable {
    private String fullName;
    private String streetAddress;
    private String apartmentNumber;
    private String zipCode;
    private String phoneNumber;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(fullName, R.string.full_name, context),
                nonEmptyString(streetAddress, R.string.street_address, context),
                nonEmptyString(zipCode, R.string.zip_code, context), // TODO: 5/4/17 change to nonEmptyStringMatching()
                nonEmptyString(phoneNumber, R.string.phone_number, context) // TODO: 5/4/17 change to nonEmptyStringMatching()
        );
    }
}
