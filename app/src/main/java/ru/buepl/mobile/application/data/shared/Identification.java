package ru.buepl.mobile.application.data.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class Identification {
    private String birthDate;
    private String placeOfBirth;
    private Gender gender;
    private String citizenship;

    /* Let it be known that I object to only having two options for gender */
    public enum Gender {
        MALE, FEMALE
    }
}
