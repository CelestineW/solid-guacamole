package ru.buepl.mobile.application.data.professional_development;

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
public final class Education implements Validatable {
    private String educationLevel;
    // TODO: 4/30/17 somehow attach diploma photo
    private SupportingDocumentation supportingDocumentation;
    private String foreignLanguage;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(educationLevel, R.string.education_level, context),
                mapErrorsForSection(supportingDocumentation, R.string.supporting_documentation, context)
        );
    }
}
