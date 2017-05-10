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
public final class AdvancePlacementDocument implements Validatable {
    private String documentTitle;
    private String documentNumber;
    private String issuedBy;
    private String issuedDate;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(documentTitle, R.string.document_title, context),
                nonEmptyString(documentNumber, R.string.document_number, context),
                nonEmptyString(issuedBy, R.string.issued_by, context),
                nonEmptyString(issuedDate, R.string.issue_date, context)
        );
    }
}
