package ru.buepl.mobile.application.data.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class PassportInfo {
    private String number;
    private String issueData;
    private String issuedBy;
}
