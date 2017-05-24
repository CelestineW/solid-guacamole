package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParentsInformationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextFatherName, editTextMotherName;
    EditText editTextFatherContact, editTextMotherContact;
    EditText editTextFatherWork, editTextMotherWork;
    EditText editTextFatherJobTitle, editTextMotherJobTitle;

    Button parentsInformationNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_information);

        editTextFatherName = (EditText)findViewById(R.id.parents_information_father_name);
        editTextFatherContact = (EditText)findViewById(R.id.parents_information_father_contact);
        editTextFatherWork = (EditText)findViewById(R.id.parents_information_father_work);
        editTextFatherJobTitle = (EditText)findViewById(R.id.parents_information_father_job_title);

        editTextMotherName = (EditText)findViewById(R.id.parents_information_mother_name);
        editTextMotherContact = (EditText)findViewById(R.id.parents_information_mother_contact);
        editTextMotherWork = (EditText)findViewById(R.id.parents_information_mother_work);
        editTextMotherJobTitle = (EditText)findViewById(R.id.parents_information_mother_job_title);

        parentsInformationNextButton = (Button)findViewById(R.id.parents_information_next_button);
        parentsInformationNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.parents_information_next_button:

                String fatherNameTxt = editTextFatherName.getText().toString();
                String fatherContactTxt = editTextFatherContact.getText().toString();
                String fatherWorkTxt = editTextFatherWork.getText().toString();
                String fatherJobTitleTxt = editTextFatherJobTitle.getText().toString();

                String motherNameTxt = editTextMotherName.getText().toString();
                String motherContactTxt = editTextMotherContact.getText().toString();
                String motherWorkTxt = editTextMotherWork.getText().toString();
                String motherJobTitleTxt = editTextMotherJobTitle.getText().toString();

                Intent specialRequirementIntent = new Intent(this, SpecialRequirementActivity.class);
                startActivity(specialRequirementIntent);
                break;
        }
    }
}
