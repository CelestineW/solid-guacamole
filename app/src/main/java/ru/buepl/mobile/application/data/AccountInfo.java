package ru.buepl.mobile.application.data;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.buepl.mobile.application.R;
import ru.buepl.mobile.application.data.validation.Validatable;
import ru.buepl.mobile.application.data.validation.ValidationError;

import static ru.buepl.mobile.application.data.validation.Validation.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public final class AccountInfo implements Validatable {
    private String firstName;
    private String middleInitial;
    private String lastName;

    private String address;
    private String city;
    private String zipCode;
    private String country;
    private String phone;

    private String email;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(firstName, R.string.first_name, context),
                nonEmptyString(lastName, R.string.last_name, context),
                nonEmptyString(address, R.string.address, context),
                nonEmptyString(city, R.string.city, context),
                nonEmptyString(zipCode, R.string.zip_code, context),
                nonEmptyString(country, R.string.country, context),
                nonEmptyString(email, R.string.email, context)
        );
    }
}
