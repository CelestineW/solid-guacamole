package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class IndividualParentInfo {
    private String name;
    private String contactInfo;
    private String placeOfWork;
    private String jobTitle;
}
