package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.AcademicInfo;
import ru.buepl.mobile.application.data.shared.SupportingDocumentation;
import ru.buepl.mobile.application.util.Toaster;

public class AcademicInformationActivity extends LoggedInActivity implements View.OnClickListener {

    EditText documentNo, issueDate, registrationNo, qualification, specialization, issuedBy;
    EditText foreignLanguage;
    Spinner currentEducation;
    Spinner supportingDocument;

    String eduLvl = "";
    String docType = "";

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_information);

        supportingDocument = (Spinner) findViewById(R.id.edu_documentation);

        documentNo = (EditText) findViewById(R.id.higher_ed_textDocumentNo);
        issueDate = (EditText) findViewById(R.id.higher_ed_textIssueDate);
        registrationNo = (EditText) findViewById(R.id.higher_ed_textRegistrationNo);
        qualification = (EditText) findViewById(R.id.higher_ed_textQualification);
        specialization = (EditText) findViewById(R.id.higher_ed_textSpecification);
        issuedBy = (EditText) findViewById(R.id.higher_ed_issuedBy);

        foreignLanguage = (EditText) findViewById(R.id.higher_ed_textForeignLanguage);

        nextButton = (Button) findViewById(R.id.academic_info_next);
        nextButton.setOnClickListener(this);

        currentEducation = (Spinner) findViewById(R.id.current_edu_level);
        final String[] educationLevels = new String[]{"", "High school diploma", "Associate 1", "Associate 2", "Associate 3", "Associate 4", "Associate 5", "BA/BS"};

        ArrayAdapter<String> educationDegrees = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, educationLevels);
        currentEducation.setAdapter(educationDegrees);
        currentEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eduLvl = educationLevels[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                eduLvl = "";
            }
        });


        supportingDocument = (Spinner) findViewById(R.id.edu_documentation);
        final String[] documentTypes = new String[]{"", "HS diploma", "BA/BS", "MA/MS", "Other"};
        ArrayAdapter<String> documents = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, documentTypes);
        supportingDocument.setAdapter(documents);
        supportingDocument.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                docType = documentTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                docType = "";
            }
        });

        // Load saved data
        AcademicInfo academicInfo = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getAcademicInfo();
        if (academicInfo != null) {
            SupportingDocumentation supportingDocumentation = academicInfo.getSupportingDocumentation();
            if (supportingDocumentation != null) {
                documentNo.setText(supportingDocumentation.getDocumentNumber());
                issueDate.setText(supportingDocumentation.getIssueDate());
                issuedBy.setText(supportingDocumentation.getIssuedBy());
                qualification.setText(supportingDocumentation.getQualification());
                specialization.setText(supportingDocumentation.getSpecialization());
                registrationNo.setText(supportingDocumentation.getRegistrationNumber());
            }
            foreignLanguage.setText(academicInfo.getForeignLanguage());
            int index = educationDegrees.getPosition(academicInfo.getCurrentEducationLevel());
            if (index >= 0) {
                currentEducation.setSelection(index);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.academic_info_next:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AcademicInformationActivity.this, TranscriptActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(AcademicInformationActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        SupportingDocumentation supportingDocumentation = SupportingDocumentation.builder()
                .documentNumber(documentNo.getText().toString().trim())
                .issueDate(issueDate.getText().toString().trim())
                .issuedBy(issuedBy.getText().toString().trim())
                .registrationNumber(registrationNo.getText().toString().trim())
                .specialization(specialization.getText().toString().trim())
                .qualification(qualification.getText().toString().trim())
                .build();

        AcademicInfo academicInfo = AcademicInfo.builder()
                .currentEducationLevel(eduLvl)
                .foreignLanguage(foreignLanguage.getText().toString().trim())
                .supportingDocumentation(supportingDocumentation)
                .supportingDocumentationType(docType)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication().setAcademicInfo(academicInfo);
        return application;
    }
}
