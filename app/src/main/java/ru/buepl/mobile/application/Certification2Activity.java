package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import ru.buepl.mobile.application.data.Application;

public class Certification2Activity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextCertifi2FirstName;
    EditText editTextCertifi2LastName;
    EditText editTextCertifi2Date;

    String certifi2Answ;
    Button certifi2NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification2);

        editTextCertifi2FirstName = (EditText)findViewById(R.id.certifi_2_first_name);
        editTextCertifi2LastName = (EditText)findViewById(R.id.certifi_2_last_name);
        editTextCertifi2Date = (EditText)findViewById(R.id.certifi_2_date);

        certifi2NextButton = (Button)findViewById(R.id.certification_2_next_button);
        certifi2NextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.certification_2_next_button:

                String certifi2FirstNameTxt = editTextCertifi2FirstName.getText().toString();
                String certifi2LastNameTxt = editTextCertifi2LastName.getText().toString();
                String certifi2Date = editTextCertifi2Date.getText().toString();

                String certifi2AnswTxt = certifi2Answ;

                Intent certifi3Intent = new Intent(this, Certification3Activity.class);
                startActivity(certifi3Intent);

                break;
        }
    }

    public void onRadioButtonClicked(View view){
        //button checked?
        boolean checked = ((RadioButton) view).isChecked();

        //which button checked?
        switch (view.getId()){
            case R.id.certifi_2_radio_yes:
                if(checked){
                    certifi2Answ = "Yes";
                }
                break;
            case R.id.certifi_2_radio_no:
                if(checked){
                    certifi2Answ = "No";
                }
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
