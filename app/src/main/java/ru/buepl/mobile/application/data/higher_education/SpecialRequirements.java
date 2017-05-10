package ru.buepl.mobile.application.data.higher_education;

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
public final class SpecialRequirements implements Validatable {
    private TestSpecialRequirement requirement1;
    private TestSpecialRequirement requirement2;
    private TestSpecialRequirement requirement3;
    private TestSpecialRequirement requirement4;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                mapErrorsForSection(nullOrValid(requirement1, context),
                        R.string.special_requirements_for_test_1, context),
                mapErrorsForSection(nullOrValid(requirement2, context),
                        R.string.special_requirements_for_test_2, context),
                mapErrorsForSection(nullOrValid(requirement3, context),
                        R.string.special_requirements_for_test_3, context),
                mapErrorsForSection(nullOrValid(requirement4, context),
                        R.string.special_requirements_for_test_4, context)
        );
    }
}
