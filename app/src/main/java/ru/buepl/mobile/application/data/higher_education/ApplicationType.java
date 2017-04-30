package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class ApplicationType {
    private int yearNumber;
    private CourseType courseType;
    private String programChoice1; // TODO: 4/29/17 maybe make these enums?
    private String programChoice2;
    private String programChoice3;

    public enum CourseType {
        EVENING, DAY, CORRESPONDENCE
    }
}
