package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class ParentsInfo {
    private IndividualParentInfo fatherInfo;
    private IndividualParentInfo motherInfo;
}
