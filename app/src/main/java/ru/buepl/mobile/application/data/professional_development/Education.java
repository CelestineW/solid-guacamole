package ru.buepl.mobile.application.data.professional_development;

import lombok.Builder;
import lombok.Data;
import ru.buepl.mobile.application.data.shared.SupportingDocumentation;

@Data
@Builder(builderClassName = "Builder")
public final class Education {
    private String educationLevel;
    // TODO: 4/30/17 somehow attach diploma photo
    private SupportingDocumentation supportingDocumentation;
    private String foreignLanguage;
}
