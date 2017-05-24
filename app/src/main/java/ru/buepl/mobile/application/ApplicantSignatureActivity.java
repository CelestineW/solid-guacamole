package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ApplicantSignatureActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextApplicantSign;
    EditText editTextApplicantSignDate;
    EditText editTextApplicantRepresentSign;
    EditText editTextApplicantRepresentSignDate;

    Button applicantSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_signature);

        editTextApplicantSign = (EditText)findViewById(R.id.applicant_sign);
        editTextApplicantSignDate = (EditText)findViewById(R.id.applicant_sign_date_1);
        editTextApplicantRepresentSign = (EditText)findViewById(R.id.applicant_represent_sign);
        editTextApplicantRepresentSignDate = (EditText)findViewById(R.id.applicant_sign_date_2);

        applicantSubmitButton = (Button)findViewById(R.id.applicant_sign_submit_button);
        applicantSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.applicant_sign_submit_button:

                String applicantSignTxt = editTextApplicantSign.getText().toString();
                String applicantSignDateTxt = editTextApplicantRepresentSignDate.getText().toString();
                String applicantRepresentSignTxt = editTextApplicantRepresentSign.getText().toString();
                String applicantRepresentSignDateTxt = editTextApplicantRepresentSignDate.getText().toString();

                Intent thankyouIntent = new Intent(this, ProDevThankyouActivity.class);
                startActivity(thankyouIntent);

                break;
        }
    }
}
