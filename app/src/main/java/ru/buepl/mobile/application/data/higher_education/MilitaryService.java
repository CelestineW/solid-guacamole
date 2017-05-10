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
public final class MilitaryService implements Validatable {
    private Boolean eligible;
    private String documentNumber;
    private String issueDate;
    private String issuedBy;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonNull(eligible, R.string.military_eligibility, context),
                nonEmptyString(documentNumber, R.string.document_number, context),
                nonEmptyString(issueDate, R.string.issue_date, context),
                nonEmptyString(issuedBy, R.string.issued_by, context)
        );
    }
}
