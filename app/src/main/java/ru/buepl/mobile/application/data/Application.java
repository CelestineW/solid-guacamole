package ru.buepl.mobile.application.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.buepl.mobile.application.data.shared.Identification;
import ru.buepl.mobile.application.data.shared.PersonalInfo;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public final class Application {
    private PersonalInfo personalInfo;
    private Identification identification;
    private Type type;
    private HigherEducationApplication higherEducationApplication;
    private ProfessionalDevelopmentApplication professionalDevelopmentApplication;

    public static Application empty() {
        return Application.builder()
                .type(Type.NONE)
                .build();
    }

    public enum Type {
        NONE, HIGHER_EDUCATION, PROFESSIONAL_DEVELOPMENT
    }
}
