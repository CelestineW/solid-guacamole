package ru.buepl.mobile.application.data.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class PersonalInfo {
    private String homePhone;
    private String cellPhone;
    private PassportInfo passportInfo;
}
