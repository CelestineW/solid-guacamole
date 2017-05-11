package ru.buepl.mobile.application.data.shared;

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
public final class PersonalInfo implements Validatable {
    private String homePhone;
    private String cellPhone;
    private PassportInfo passportInfo;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(homePhone, R.string.home_phone, context),
                nonEmptyString(cellPhone, R.string.cell_phone, context),
                mapErrorsForSection(passportInfo, R.string.passport_info, context)
        );
    }
}
