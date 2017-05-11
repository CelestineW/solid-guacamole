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
public final class ApplicationType implements Validatable {
    private Integer yearNumber;
    private CourseType courseType;
    private String programChoice1; // TODO: 4/29/17 maybe make these enums?
    private String programChoice2;
    private String programChoice3;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                nonNull(yearNumber, R.string.year_number, context),
                nonNull(courseType, R.string.course_type, context),
                nonEmptyString(programChoice1, R.string.program_choice_1, context),
                nonEmptyString(programChoice2, R.string.program_choice_2, context),
                nonEmptyString(programChoice3, R.string.program_choice_3, context)
        );
    }

    public enum CourseType {
        EVENING, DAY, CORRESPONDENCE
    }
}
