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
public final class SupportingDocumentation implements Validatable {
    private String documentNumber;
    private String issueDate;
    private String issuedBy;
    private String registrationNumber;
    private String qualification;
    private String specialization;

    // TODO: 4/29/17 attach document somehow??

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(documentNumber, R.string.document_number, context),
                nonEmptyString(issueDate, R.string.issue_date, context),
                nonEmptyString(issuedBy, R.string.issued_by, context),
                nonEmptyString(registrationNumber, R.string.registration_number, context),
                nonEmptyString(qualification, R.string.qualification, context),
                nonEmptyString(specialization, R.string.specialization, context)
        );
    }
}
