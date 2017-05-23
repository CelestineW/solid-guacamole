package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UniversityEntranceExamsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextExamDate, editTextExamLocation;
    EditText editTextExamSubject1, editTextExamScore1, editTextExamDate1;
    EditText editTextExamSubject2, editTextExamScore2, editTextExamDate2;
    EditText editTextExamSubject3, editTextExamScore3, editTextExamDate3;
    EditText editTextExamSubject4, editTextExamScore4, editTextExamDate4;

    Button universityEntranceExamNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_entrance_exams);

        editTextExamDate = (EditText)findViewById(R.id.university_entrance_exam_date);
        editTextExamLocation = (EditText)findViewById(R.id.university_entrance_exam_location);

        editTextExamSubject1 = (EditText)findViewById(R.id.university_entrance_exam_subject_1);
        editTextExamScore1 = (EditText)findViewById(R.id.university_entrance_exam_score_1);
        editTextExamDate1 = (EditText)findViewById(R.id.university_entrance_exam_date_1);

        editTextExamSubject2 = (EditText)findViewById(R.id.university_entrance_exam_subject_2);
        editTextExamScore2 = (EditText)findViewById(R.id.university_entrance_exam_score_2);
        editTextExamDate2 = (EditText)findViewById(R.id.university_entrance_exam_date_2);

        editTextExamSubject3 = (EditText)findViewById(R.id.university_entrance_exam_subject_3);
        editTextExamScore3 = (EditText)findViewById(R.id.university_entrance_exam_score_3);
        editTextExamDate3 = (EditText)findViewById(R.id.university_entrance_exam_date_3);

        editTextExamSubject4 = (EditText)findViewById(R.id.university_entrance_exam_subject_4);
        editTextExamScore4 = (EditText)findViewById(R.id.university_entrance_exam_score_4);
        editTextExamDate4 = (EditText)findViewById(R.id.university_entrance_exam_date_4);

        universityEntranceExamNextButton = (Button)findViewById(R.id.university_entrance_next_button);
        universityEntranceExamNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.university_entrance_next_button:

                String examDateTxt = editTextExamDate.getText().toString();
                String examLocationTxt = editTextExamLocation.getText().toString();

                String examSubject1Txt = editTextExamSubject1.getText().toString();
                String examScore1Txt = editTextExamScore1.getText().toString();
                String examDate1Txt = editTextExamDate1.getText().toString();

                String examSubject2Txt = editTextExamSubject2.getText().toString();
                String examScore2Txt = editTextExamScore2.getText().toString();
                String examDate2Txt = editTextExamDate2.getText().toString();

                String examSubject3Txt = editTextExamSubject3.getText().toString();
                String examScore3Txt = editTextExamScore3.getText().toString();
                String examDate3Txt = editTextExamDate3.getText().toString();

                String examSubject4Txt = editTextExamSubject4.getText().toString();
                String examScore4Txt = editTextExamScore4.getText().toString();
                String examDate4Txt = editTextExamDate4.getText().toString();

                Intent parentsInformationIntent = new Intent(this, ParentsInformationActivity.class);
                startActivity(parentsInformationIntent);

                break;
        }
    }
}
