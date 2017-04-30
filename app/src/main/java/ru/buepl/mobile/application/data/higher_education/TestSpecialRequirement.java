package ru.buepl.mobile.application.data.higher_education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public final class TestSpecialRequirement {
    private String title;
    private String specialRequirements;
}
