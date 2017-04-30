package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;
import ru.buepl.mobile.application.data.shared.SupportingDocumentation;

@Data
@Builder(builderClassName = "Builder")
public final class AcademicInfo {
    private String currentEducationLevel; // TODO: 4/29/17 should these be enum? idk
    private String supportingDocumentationType;
    private SupportingDocumentation supportingDocumentation;
    private String foreignLanguage;
}
