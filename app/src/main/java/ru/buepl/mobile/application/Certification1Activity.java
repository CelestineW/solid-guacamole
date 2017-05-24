package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.buepl.mobile.application.data.Application;

public class Certification1Activity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextCertifi1FirstName;
    EditText editTextCertifi1LastName;
    EditText editTextCertifi1Date;

    Button certifi1NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification1);

        editTextCertifi1FirstName = (EditText)findViewById(R.id.certification_1_first_name);
        editTextCertifi1LastName = (EditText)findViewById(R.id.certification_1_last_name);
        editTextCertifi1Date = (EditText)findViewById(R.id.certification_1_date);

        certifi1NextButton = (Button)findViewById(R.id.certification_1_next_button);
        certifi1NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_1_next_button:

                String certifi1FirstNameTxt = editTextCertifi1FirstName.getText().toString();
                String certifi1LastNameTxt = editTextCertifi1LastName.getText().toString();
                String certifi1DataTxt = editTextCertifi1Date.getText().toString();

                Intent certifi2Intent = new Intent(this,Certification2Activity.class);
                startActivity(certifi2Intent);

                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
