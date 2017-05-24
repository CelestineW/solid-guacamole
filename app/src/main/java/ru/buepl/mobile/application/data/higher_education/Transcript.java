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
public final class Transcript implements Validatable {
    private Boolean hasTranscript;
    private TranscriptInfo info;
    private ScholasticCompetition scholasticCompetition;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        if (hasTranscript != null && hasTranscript) {
            return aggregateErrors(
                    mapErrorsForSection(info, R.string.transcript_info, context),
                    mapErrorsForSection(nullOrValid(scholasticCompetition, context),
                            R.string.scholastic_competition, context)
            );
        } else {
            return aggregateErrors(
                    mapErrorsForSection(nonNull(hasTranscript, R.string.transcript_yes_no, context), R.string.transcript_info, context),
                    mapErrorsForSection(nullOrValid(scholasticCompetition, context),
                            R.string.scholastic_competition, context)
            );
        }
    }
}
