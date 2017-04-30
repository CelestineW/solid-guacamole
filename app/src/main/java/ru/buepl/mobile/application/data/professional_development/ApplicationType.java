package ru.buepl.mobile.application.data.professional_development;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public final class ApplicationType {
    private String course;
    private Level level;

    public enum Level {
        A, B, C
    }
}
