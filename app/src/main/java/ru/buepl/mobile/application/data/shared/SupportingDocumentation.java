package ru.buepl.mobile.application.data.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class SupportingDocumentation {
    private String documentNumber;
    private String issueDate;
    private String issuedBy;
    private String registrationNumber;
    private String qualification;
    private String specialization;

    // TODO: 4/29/17 attach document somehow??
}
