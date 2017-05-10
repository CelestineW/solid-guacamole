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
public final class AdvancePlacement implements Validatable {
    private AdvancePlacementDocument document1;
    private AdvancePlacementDocument document2;

    @NonNull
    @Override
    public List<ValidationError> validate(@NonNull Context context) {
        return aggregateErrors(
                mapErrorsForSection(document1, R.string.advance_placement_doc_1, context),
                mapErrorsForSection(document2, R.string.advance_placement_doc_2, context)
        );
    }
}
