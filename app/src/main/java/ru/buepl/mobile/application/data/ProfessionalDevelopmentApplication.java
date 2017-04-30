package ru.buepl.mobile.application.data;

import lombok.Builder;
import lombok.Data;
import ru.buepl.mobile.application.data.professional_development.ApplicationType;
import ru.buepl.mobile.application.data.professional_development.Education;
import ru.buepl.mobile.application.data.shared.Identification;
import ru.buepl.mobile.application.data.shared.PersonalInfo;

@Data
@Builder(builderClassName = "Builder")
public final class ProfessionalDevelopmentApplication {
    private PersonalInfo personalInfo;
    private ApplicationType applicationType;
    private Identification identification;
    private Education education;
}
