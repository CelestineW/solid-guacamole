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
public final class UniversityEntranceExams implements Validatable {
    private String date;
    private String location;
    private ExamResult result1;
    private ExamResult result2;
    private ExamResult result3;
    private ExamResult result4;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonEmptyString(date, R.string.date, context),
                nonEmptyString(location, R.string.location, context),
                mapErrorsForSection(result1, R.string.test_result_1, context),
                mapErrorsForSection(result2, R.string.test_result_2, context),
                mapErrorsForSection(result3, R.string.test_result_3, context),
                mapErrorsForSection(result4, R.string.test_result_4, context)
        );
    }
}
