package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.buepl.mobile.application.data.shared.Identification;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton;

    EditText editTextHomePhoneNumber;
    EditText editTextCellPhoneNumber;
    EditText editTextPassportNumber;
    EditText editTextIssueDate;
    EditText editTextIssuedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        editTextHomePhoneNumber = (EditText) findViewById(R.id.editText7);
        editTextCellPhoneNumber = (EditText) findViewById(R.id.editText9);
        editTextPassportNumber = (EditText) findViewById(R.id.editText12);
        editTextIssueDate = (EditText) findViewById(R.id.editText14);
        editTextIssuedBy = (EditText) findViewById(R.id.editText23);

        nextButton = (Button) findViewById(R.id.button8);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button8:

                String homePhoneNumberTxt = editTextHomePhoneNumber.getText().toString();
                String cellPhoneNumberTxt = editTextCellPhoneNumber.getText().toString();
                String passportNumberTxt = editTextPassportNumber.getText().toString();
                String issueDateTxt = editTextIssueDate.getText().toString();
                String issuedByTxt = editTextIssuedBy.getText().toString();

                Intent identificationIntent = new Intent(this, IdentificationActivity.class);
                startActivity(identificationIntent);

        }
    }
}
