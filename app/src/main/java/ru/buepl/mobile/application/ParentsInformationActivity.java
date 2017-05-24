package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.IndividualParentInfo;
import ru.buepl.mobile.application.data.higher_education.ParentsInfo;
import ru.buepl.mobile.application.util.Toaster;

public class ParentsInformationActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextFatherName, editTextMotherName;
    EditText editTextFatherContact, editTextMotherContact;
    EditText editTextFatherWork, editTextMotherWork;
    EditText editTextFatherJobTitle, editTextMotherJobTitle;

    Button parentsInformationNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_information);

        editTextFatherName = (EditText) findViewById(R.id.parents_information_father_name);
        editTextFatherContact = (EditText) findViewById(R.id.parents_information_father_contact);
        editTextFatherWork = (EditText) findViewById(R.id.parents_information_father_work);
        editTextFatherJobTitle = (EditText) findViewById(R.id.parents_information_father_job_title);

        editTextMotherName = (EditText) findViewById(R.id.parents_information_mother_name);
        editTextMotherContact = (EditText) findViewById(R.id.parents_information_mother_contact);
        editTextMotherWork = (EditText) findViewById(R.id.parents_information_mother_work);
        editTextMotherJobTitle = (EditText) findViewById(R.id.parents_information_mother_job_title);

        parentsInformationNextButton = (Button) findViewById(R.id.parents_information_next_button);
        parentsInformationNextButton.setOnClickListener(this);

        // Load saved data
        ParentsInfo parentsInfo = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getParentsInfo();
        if (parentsInfo != null) {
            IndividualParentInfo fatherInfo = parentsInfo.getFatherInfo();
            if (fatherInfo != null) {
                editTextFatherName.setText(fatherInfo.getName());
                editTextFatherContact.setText(fatherInfo.getContactInfo());
                editTextFatherWork.setText(fatherInfo.getPlaceOfWork());
                editTextFatherJobTitle.setText(fatherInfo.getJobTitle());
            }
            IndividualParentInfo motherInfo = parentsInfo.getMotherInfo();
            if (motherInfo != null) {
                editTextMotherName.setText(motherInfo.getName());
                editTextMotherContact.setText(motherInfo.getContactInfo());
                editTextMotherWork.setText(motherInfo.getPlaceOfWork());
                editTextMotherJobTitle.setText(motherInfo.getJobTitle());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parents_information_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ParentsInformationActivity.this, SpecialRequirementActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ParentsInformationActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        IndividualParentInfo fatherInfo = IndividualParentInfo.builder()
                .name(editTextFatherName.getText().toString().trim())
                .contactInfo(editTextFatherContact.getText().toString().trim())
                .placeOfWork(editTextFatherWork.getText().toString().trim())
                .jobTitle(editTextFatherJobTitle.getText().toString().trim())
                .build();
        IndividualParentInfo motherInfo = IndividualParentInfo.builder()
                .name(editTextMotherName.getText().toString().trim())
                .contactInfo(editTextMotherContact.getText().toString().trim())
                .placeOfWork(editTextMotherWork.getText().toString().trim())
                .jobTitle(editTextMotherJobTitle.getText().toString().trim())
                .build();

        ParentsInfo parentsInfo = ParentsInfo.builder()
                .fatherInfo(fatherInfo)
                .motherInfo(motherInfo)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setParentsInfo(parentsInfo);
        return application;
    }
}
