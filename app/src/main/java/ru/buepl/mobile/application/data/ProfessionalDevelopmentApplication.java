package ru.buepl.mobile.application.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.buepl.mobile.application.data.professional_development.ApplicationType;
import ru.buepl.mobile.application.data.professional_development.Education;
import ru.buepl.mobile.application.data.shared.Identification;
import ru.buepl.mobile.application.data.shared.PersonalInfo;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public final class ProfessionalDevelopmentApplication {
    private ApplicationType applicationType;
    private Education education;
}
