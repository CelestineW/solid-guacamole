package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class SpecialRequirements {
    private TestSpecialRequirement requirement1;
    private TestSpecialRequirement requirement2;
    private TestSpecialRequirement requirement3;
    private TestSpecialRequirement requirement4;
}
