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
import ru.buepl.mobile.application.data.higher_education.NationalAcceptanceTest;
import ru.buepl.mobile.application.util.Toaster;

public class NationalAcceptanceTestActivity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextTestDate, editTextTestLocation;
    EditText editTextSubject1, editTextScore1, editTextDate1;
    EditText editTextSubject2, editTextScore2, editTextDate2;
    EditText editTextSubject3, editTextScore3, editTextDate3;
    EditText editTextSubject4, editTextScore4, editTextDate4;
    EditText editTextSubject5, editTextScore5, editTextDate5;
    EditText editTextSubject6, editTextScore6, editTextDate6;

    Button nationalTestNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national_acceptance_test);

        editTextTestDate = (EditText)findViewById(R.id.national_test_date);
        editTextTestLocation = (EditText)findViewById(R.id.national_test_location);

        editTextSubject1 = (EditText)findViewById(R.id.national_test_subject_1);
        editTextScore1 = (EditText)findViewById(R.id.national_test_score_1);
        editTextDate1 = (EditText)findViewById(R.id.national_test_date_1);

        editTextSubject2 = (EditText)findViewById(R.id.national_test_subject_2);
        editTextScore2 = (EditText)findViewById(R.id.national_test_score_2);
        editTextDate2 = (EditText)findViewById(R.id.national_test_date_2);

        editTextSubject3 = (EditText)findViewById(R.id.national_test_subject_3);
        editTextScore3 = (EditText)findViewById(R.id.national_test_score_3);
        editTextDate3 = (EditText)findViewById(R.id.national_test_date_3);

        editTextSubject4 = (EditText)findViewById(R.id.national_test_subject_4);
        editTextScore4 = (EditText)findViewById(R.id.national_test_score_4);
        editTextDate4 = (EditText)findViewById(R.id.national_test_date_4);

        editTextSubject5 = (EditText)findViewById(R.id.national_test_subject_5);
        editTextScore5 = (EditText)findViewById(R.id.national_test_score_5);
        editTextDate5 = (EditText)findViewById(R.id.national_test_date_5);

        editTextSubject6 = (EditText)findViewById(R.id.national_test_subject_6);
        editTextScore6 = (EditText)findViewById(R.id.national_test_score_6);
        editTextDate6 = (EditText)findViewById(R.id.national_test_date_6);

        nationalTestNextButton = (Button)findViewById(R.id.national_test_next_button);
        nationalTestNextButton.setOnClickListener(this);

        // Load saved data
        NationalAcceptanceTest test = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getNationalAcceptanceTest();
        if (test != null) {
            editTextTestDate.setText(test.getDate());
            editTextTestLocation.setText(test.getLocation());

            ExamResult result1 = test.getResult1();
            if (result1 != null) {
                editTextSubject1.setText(result1.getSubject());
                editTextScore1.setText(result1.getScore());
                editTextDate1.setText(result1.getDate());
            }
            ExamResult result2 = test.getResult2();
            if (result2 != null) {
                editTextSubject2.setText(result2.getSubject());
                editTextScore2.setText(result2.getScore());
                editTextDate2.setText(result2.getDate());
            }
            ExamResult result3 = test.getResult3();
            if (result3 != null) {
                editTextSubject3.setText(result3.getSubject());
                editTextScore3.setText(result3.getScore());
                editTextDate3.setText(result3.getDate());
            }
            ExamResult result4 = test.getResult4();
            if (result4 != null) {
                editTextSubject4.setText(result4.getSubject());
                editTextScore4.setText(result4.getScore());
                editTextDate4.setText(result4.getDate());
            }
            ExamResult result5 = test.getResult5();
            if (result5 != null) {
                editTextSubject5.setText(result5.getSubject());
                editTextScore5.setText(result5.getScore());
                editTextDate5.setText(result5.getDate());
            }
            ExamResult result6 = test.getResult6();
            if (result6 != null) {
                editTextSubject6.setText(result6.getSubject());
                editTextScore6.setText(result6.getScore());
                editTextDate6.setText(result6.getDate());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.national_test_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(NationalAcceptanceTestActivity.this, UniversityEntranceExamsActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(NationalAcceptanceTestActivity.this, task.getException());
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
                .subject(editTextSubject1.getText().toString().trim())
                .score(editTextScore1.getText().toString().trim())
                .date(editTextDate1.getText().toString().trim())
                .build();
        ExamResult result2 = ExamResult.builder()
                .subject(editTextSubject2.getText().toString().trim())
                .score(editTextScore2.getText().toString().trim())
                .date(editTextDate2.getText().toString().trim())
                .build();
        ExamResult result3 = ExamResult.builder()
                .subject(editTextSubject3.getText().toString().trim())
                .score(editTextScore3.getText().toString().trim())
                .date(editTextDate3.getText().toString().trim())
                .build();
        ExamResult result4 = ExamResult.builder()
                .subject(editTextSubject4.getText().toString().trim())
                .score(editTextScore4.getText().toString().trim())
                .date(editTextDate4.getText().toString().trim())
                .build();
        ExamResult result5 = ExamResult.builder()
                .subject(editTextSubject5.getText().toString().trim())
                .score(editTextScore5.getText().toString().trim())
                .date(editTextDate5.getText().toString().trim())
                .build();
        ExamResult result6 = ExamResult.builder()
                .subject(editTextSubject6.getText().toString().trim())
                .score(editTextScore6.getText().toString().trim())
                .date(editTextDate6.getText().toString().trim())
                .build();

        NationalAcceptanceTest test = NationalAcceptanceTest.builder()
                .date(editTextTestDate.getText().toString().trim())
                .location(editTextTestLocation.getText().toString().trim())
                .result1(result1)
                .result2(result2)
                .result3(result3)
                .result4(result4)
                .result5(result5)
                .result6(result6)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setNationalAcceptanceTest(test);
        return application;
    }
}
