package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class ExamResult {
    private String subject;
    private String score;
    private String date;
}
