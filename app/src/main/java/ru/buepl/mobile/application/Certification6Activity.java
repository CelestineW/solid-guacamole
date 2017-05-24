package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.buepl.mobile.application.data.Application;

public class Certification6Activity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextCertifi6FirstName;
    EditText editTextCertifi6LastName;
    EditText editTextCertifi6Date;

    Button certifi6NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification6);

        editTextCertifi6FirstName = (EditText)findViewById(R.id.certifi_6_first_name);
        editTextCertifi6LastName = (EditText)findViewById(R.id.certifi_6_last_name);
        editTextCertifi6Date = (EditText)findViewById(R.id.certifi_6_date);

        certifi6NextButton = (Button)findViewById(R.id.certification_6_next_button);
        certifi6NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_6_next_button:

                String certifi6FirstNameTxt = editTextCertifi6FirstName.getText().toString();
                String certifi6LastNameTxt = editTextCertifi6LastName.getText().toString();
                String certifi6Date = editTextCertifi6Date.getText().toString();

                Intent applicantSignature6Intent = new Intent(this, ApplicantSignatureActivity.class);
                startActivity(applicantSignature6Intent);

                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
