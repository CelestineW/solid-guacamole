package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class Certifications {
    private String certification1Date;
    private Boolean certification2Yes;
    private String certification2Date;
    private String certification3Date;
    private String certification4Date;
    private String certification5Date;
    private String certification6Date;
}
