package ru.buepl.mobile.application.data.higher_education;

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
public final class TranscriptInfo implements Validatable {
    private String documentNumber;
    private String issuedBy;
    private String issueDate;

    // TODO: 4/29/17 attach document somehow

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(documentNumber, R.string.document_number, context),
                nonEmptyString(issuedBy, R.string.issued_by, context),
                nonEmptyString(issueDate, R.string.issue_date, context)
        );
    }
}
