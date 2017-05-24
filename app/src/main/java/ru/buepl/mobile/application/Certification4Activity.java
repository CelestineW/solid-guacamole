package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.buepl.mobile.application.data.Application;

public class Certification4Activity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextCertifi4FirstName;
    EditText editTextCertifi4LastName;
    EditText editTextCertifi4Date;

    Button certifi4NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification4);

        editTextCertifi4FirstName = (EditText)findViewById(R.id.certifi_4_first_name);
        editTextCertifi4LastName = (EditText)findViewById(R.id.certifi_4_last_name);
        editTextCertifi4Date = (EditText)findViewById(R.id.certifi_4_date);

        certifi4NextButton = (Button)findViewById(R.id.certification_4_next_button);
        certifi4NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_4_next_button:

                String certifi4FirstNameTxt = editTextCertifi4FirstName.getText().toString();
                String certifi4LastNameTxt = editTextCertifi4LastName.getText().toString();
                String certifi4Date = editTextCertifi4Date.getText().toString();

                Intent certifi5Intent = new Intent(this, Certification5Activity.class);
                startActivity(certifi5Intent);

                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
