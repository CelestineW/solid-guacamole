package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.buepl.mobile.application.data.Application;

public class Certification5Activity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextCertifi5FirstName;
    EditText editTextCertifi5LastName;
    EditText editTextCertifi5Date;

    Button certifi5NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification5);

        editTextCertifi5FirstName = (EditText)findViewById(R.id.certifi_5_first_name);
        editTextCertifi5LastName = (EditText)findViewById(R.id.certifi_5_last_name);
        editTextCertifi5Date = (EditText)findViewById(R.id.certifi_5_date);

        certifi5NextButton = (Button)findViewById(R.id.certification_5_next_button);
        certifi5NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_5_next_button:

                String certifi5FirstNameTxt = editTextCertifi5FirstName.getText().toString();
                String certifi5LastNameTxt = editTextCertifi5LastName.getText().toString();
                String certifi5Date = editTextCertifi5Date.getText().toString();

                Intent certifi6Intent = new Intent(this, Certification6Activity.class);
                startActivity(certifi6Intent);

                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
