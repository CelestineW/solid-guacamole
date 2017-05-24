package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Certification3Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextCertifi3FirstName;
    EditText editTextCertifi3LastName;
    EditText editTextCertifi3Date;

    Button certifi3NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification3);

        editTextCertifi3FirstName = (EditText)findViewById(R.id.certifi_3_first_name);
        editTextCertifi3LastName = (EditText)findViewById(R.id.certifi_3_last_name);
        editTextCertifi3Date = (EditText)findViewById(R.id.certifi_3_date);

        certifi3NextButton = (Button)findViewById(R.id.certification_3_next_button);
        certifi3NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_3_next_button:

                String certifi3FirstNameTxt = editTextCertifi3FirstName.getText().toString();
                String certifi3LastNameTxt = editTextCertifi3LastName.getText().toString();
                String certifi3Date = editTextCertifi3Date.getText().toString();

                Intent certifi4Intent = new Intent(this, Certification4Activity.class);
                startActivity(certifi4Intent);

                break;
        }
    }
}
