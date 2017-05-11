package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AcademicInformationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText documentNo, issueDate, registrationNo, qualtification, specialization, issuedBy;
    EditText foreignLanguage;
    Spinner currentEducation;
    Spinner supportingDocument;

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_information);

        supportingDocument = (Spinner) findViewById(R.id.edu_documentation);

        documentNo = (EditText) findViewById(R.id.higher_ed_textDocumentNo);
        issueDate = (EditText) findViewById(R.id.higher_ed_textIssueDate);
        registrationNo = (EditText) findViewById(R.id.higher_ed_textRegistrationNo);
        qualtification = (EditText) findViewById(R.id.higher_ed_textQualification);
        specialization = (EditText) findViewById(R.id.higher_ed_textSpecification);
        issuedBy = (EditText) findViewById(R.id.higher_ed_issuedBy);

        foreignLanguage = (EditText) findViewById(R.id.higher_ed_textForeignLanguage);

        nextButton = (Button) findViewById(R.id.academic_info_next);
        nextButton.setOnClickListener(this);

        currentEducation = (Spinner) findViewById(R.id.current_edu_level);
        String[] educationLevels = new String[] {"High school diploma","Associate 1","Associate 2","Associate 3","Associate 4","Associate 5","BA/BS"};

        ArrayAdapter<String> educationDegrees = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, educationLevels);
        currentEducation.setAdapter(educationDegrees);


        supportingDocument = (Spinner) findViewById(R.id.edu_documentation);
        String[] documentTypes = new String[] {"HS diploma","BA/BS","MA/MS","Other"};
        ArrayAdapter<String> documents = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, documentTypes);
        supportingDocument.setAdapter(documents);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.academic_info_next:

                Intent mainMenu = new Intent(this, MainMenuActivity.class);
                startActivity(mainMenu);

                break;



        }

    }
}
