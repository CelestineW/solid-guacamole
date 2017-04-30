package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public class NationalAcceptanceTest {
    private String date;
    private String location;
    private ExamResult result1;
    private ExamResult result2;
    private ExamResult result3;
    private ExamResult result4;
    private ExamResult result5;
    private ExamResult result6;
}
