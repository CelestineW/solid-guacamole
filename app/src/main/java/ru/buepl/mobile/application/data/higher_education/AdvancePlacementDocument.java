package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class AdvancePlacementDocument {
    private String documentTitle;
    private String documentNumber;
    private String issuedBy;
    private String issuedDate;
}
