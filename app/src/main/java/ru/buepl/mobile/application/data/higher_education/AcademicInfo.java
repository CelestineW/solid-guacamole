package ru.buepl.mobile.application.data.higher_education;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import ru.buepl.mobile.application.R;
import ru.buepl.mobile.application.data.shared.SupportingDocumentation;
import ru.buepl.mobile.application.data.validation.Validatable;
import ru.buepl.mobile.application.data.validation.ValidationError;

import static ru.buepl.mobile.application.data.validation.Validation.*;

@Data
@Builder(builderClassName = "Builder")
public final class AcademicInfo implements Validatable {
    private String currentEducationLevel; // TODO: 4/29/17 should these be enum? idk
    private String supportingDocumentationType;
    private SupportingDocumentation supportingDocumentation;
    private String foreignLanguage;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(currentEducationLevel, R.string.current_education_level, context),
                nonEmptyString(supportingDocumentationType, R.string.supporting_documentation_type, context),
                mapErrorsForSection(supportingDocumentation, R.string.supporting_documentation, context),
                nonEmptyString(foreignLanguage, R.string.foreign_language, context)
        );
    }
}
