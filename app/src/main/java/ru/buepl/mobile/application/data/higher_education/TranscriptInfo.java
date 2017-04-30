package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class TranscriptInfo {
    private String documentNumber;
    private String issuedBy;
    private String issueDate;

    // TODO: 4/29/17 attach document somehow
}
