package ru.buepl.mobile.application.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.buepl.mobile.application.data.higher_education.*;
import ru.buepl.mobile.application.data.shared.Identification;
import ru.buepl.mobile.application.data.shared.PersonalInfo;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public final class HigherEducationApplication {
    private ApplicationType applicationType;
    private AcademicInfo academicInfo;
    private Transcript transcript;
    private AdvancePlacement advancePlacement;
    private MilitaryService militaryService;
    private StPetersburgAddress stPetersburgAddress;
    private NationalAcceptanceTest nationalAcceptanceTest;
    private UniversityEntranceExams universityEntranceExams;
    private ParentsInfo parentsInfo;
    private SpecialRequirements specialRequirements;
    private Boolean dormitory;
    private Address returnAddress;
    private Certifications certifications;

    // TODO: 4/30/17 somehow handle applicant and representative signatures
}
