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
public final class Transcript implements Validatable {
    private TranscriptInfo info;
    private ScholasticCompetition scholasticCompetition;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                mapErrorsForSection(info, R.string.transcript_info, context),
                mapErrorsForSection(nullOrValid(scholasticCompetition, context),
                        R.string.scholastic_competition, context)
        );
    }
}
