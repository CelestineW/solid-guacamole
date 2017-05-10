package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class IdentificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton;

    EditText editTextDateOfBirth;
    EditText editTextPlaceOfBirth;
    EditText editTextCitizenship;

    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        editTextDateOfBirth = (EditText) findViewById(R.id.editText8);
        editTextPlaceOfBirth = (EditText) findViewById(R.id.editText10);
        editTextCitizenship = (EditText) findViewById(R.id.editText11);

        nextButton = (Button) findViewById(R.id.button7);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button7:

                String dateOfBirthTxt = editTextDateOfBirth.getText().toString();
                String placeOfBirthTxt = editTextPlaceOfBirth.getText().toString();
                String citizenshipTxt = editTextCitizenship.getText().toString();
                String genderTxt = gender;

                Intent programIntent = new Intent(this, ProgramSelectionActivity.class);
                startActivity(programIntent);
        }
    }

    public void onRadioButtonClicked(View view){
        //button checked?
        boolean checked = ((RadioButton) view).isChecked();

        //which button checked?
        switch (view.getId()){
            case R.id.radioButton2:
                if(checked){
                    gender = "Female";
                }
                break;
            case R.id.radioButton3:
                if(checked){
                    gender = "Male";
                }
                break;
        }
    }

}
