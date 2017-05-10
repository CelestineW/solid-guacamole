package ru.buepl.mobile.application.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Application {
    private Type type;
    private HigherEducationApplication higherEducationApplication;
    private ProfessionalDevelopmentApplication professionalDevelopmentApplication;

    public static Application forHigherEducation(HigherEducationApplication application) {
        return new Application(Type.HIGHER_EDUCATION, application, null);
    }

    public static Application forProfessionalDevelopment(ProfessionalDevelopmentApplication application) {
        return new Application(Type.PROFESSIONAL_DEVELOPMENT, null, application);
    }

    public enum Type {
        HIGHER_EDUCATION, PROFESSIONAL_DEVELOPMENT
    }
}
