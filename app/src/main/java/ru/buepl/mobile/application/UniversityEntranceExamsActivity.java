package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.ExamResult;
import ru.buepl.mobile.application.data.higher_education.UniversityEntranceExams;

public class UniversityEntranceExamsActivity extends LoggedInActivity implements View.OnClickListener {

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

        // Load saved data
        UniversityEntranceExams universityEntranceExams = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getUniversityEntranceExams();
        if (universityEntranceExams != null) {
            editTextExamDate.setText(universityEntranceExams.getDate());
            editTextExamLocation.setText(universityEntranceExams.getLocation());
            ExamResult result1 = universityEntranceExams.getResult1();
            if (result1 != null) {
                editTextExamSubject1.setText(result1.getSubject());
                editTextExamScore1.setText(result1.getScore());
                editTextExamDate1.setText(result1.getDate());
            }
            ExamResult result2 = universityEntranceExams.getResult2();
            if (result2 != null) {
                editTextExamSubject2.setText(result2.getSubject());
                editTextExamScore2.setText(result2.getScore());
                editTextExamDate2.setText(result2.getDate());
            }
            ExamResult result3 = universityEntranceExams.getResult3();
            if (result3 != null) {
                editTextExamSubject3.setText(result3.getSubject());
                editTextExamScore3.setText(result3.getScore());
                editTextExamDate3.setText(result3.getDate());
            }
            ExamResult result4 = universityEntranceExams.getResult4();
            if (result4 != null) {
                editTextExamSubject4.setText(result4.getSubject());
                editTextExamScore4.setText(result4.getScore());
                editTextExamDate4.setText(result4.getDate());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.university_entrance_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(UniversityEntranceExamsActivity.this, ParentsInformationActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        ExamResult result1 = ExamResult.builder()
                .subject(editTextExamSubject1.getText().toString().trim())
                .score(editTextExamScore1.getText().toString().trim())
                .date(editTextExamDate1.getText().toString().trim())
                .build();
        ExamResult result2 = ExamResult.builder()
                .subject(editTextExamSubject2.getText().toString().trim())
                .score(editTextExamScore2.getText().toString().trim())
                .date(editTextExamDate2.getText().toString().trim())
                .build();
        ExamResult result3 = ExamResult.builder()
                .subject(editTextExamSubject3.getText().toString().trim())
                .score(editTextExamScore3.getText().toString().trim())
                .date(editTextExamDate3.getText().toString().trim())
                .build();
        ExamResult result4 = ExamResult.builder()
                .subject(editTextExamSubject4.getText().toString().trim())
                .score(editTextExamScore4.getText().toString().trim())
                .date(editTextExamDate4.getText().toString().trim())
                .build();

        UniversityEntranceExams universityEntranceExams = UniversityEntranceExams.builder()
                .date(editTextExamDate.getText().toString().trim())
                .location(editTextExamLocation.getText().toString().trim())
                .result1(result1)
                .result2(result2)
                .result3(result3)
                .result4(result4)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setUniversityEntranceExams(universityEntranceExams);
        return application;
    }
}
