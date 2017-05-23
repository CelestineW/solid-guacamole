package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NationalAcceptanceTestActivity extends AppCompatActivity implements View.OnClickListener{

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
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.national_test_next_button:

                String testDateTxt = editTextTestDate.getText().toString();
                String testLocationTxt = editTextTestLocation.getText().toString();

                String subject1Txt = editTextSubject1.getText().toString();
                String score1Txt = editTextScore1.getText().toString();
                String date1Txt = editTextDate1.getText().toString();

                String subject2Txt = editTextSubject2.getText().toString();
                String score2Txt = editTextScore2.getText().toString();
                String date2Txt = editTextDate2.getText().toString();

                String subject3Txt = editTextSubject3.getText().toString();
                String score3Txt = editTextScore3.getText().toString();
                String date3Txt = editTextDate3.getText().toString();

                String subject4Txt = editTextSubject4.getText().toString();
                String score4Txt = editTextScore4.getText().toString();
                String date4Txt = editTextDate4.getText().toString();

                String subject5Txt = editTextSubject5.getText().toString();
                String score5Txt = editTextScore5.getText().toString();
                String date5Txt = editTextDate5.getText().toString();

                String subject6Txt = editTextSubject6.getText().toString();
                String score6Txt = editTextScore6.getText().toString();
                String date6Txt = editTextDate6.getText().toString();

                Intent main = new Intent(this, MainMenuActivity.class);
                startActivity(main);
        }
    }
}
