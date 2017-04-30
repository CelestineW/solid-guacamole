package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class MilitaryService {
    private Boolean eligible;
    private String documentNumber;
    private String issueDate;
    private String issuedFrom;
}
