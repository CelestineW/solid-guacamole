package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class Transcript {
    private TranscriptInfo info;
    private ScholasticCompetition scholasticCompetition;
}
