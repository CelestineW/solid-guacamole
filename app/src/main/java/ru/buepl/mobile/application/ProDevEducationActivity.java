package ru.buepl.mobile.application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.professional_development.Education;
import ru.buepl.mobile.application.data.shared.SupportingDocumentation;
import ru.buepl.mobile.application.util.Toaster;

public class ProDevEducationActivity extends LoggedInActivity implements View.OnClickListener {

    Button nextButton, photoAttachButton;
    EditText editTextDocumentationNo;
    EditText editTextIssuedBy;
    EditText editTextIssueDate;
    EditText editTextRegistrationNo;
    EditText editTextQualification;
    EditText editTextSpecialization;
    EditText editTextForeignLanguage;

    String educationLevel = "";

    private ImageView imageview;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_education);

        nextButton = (Button) findViewById(R.id.pro_dev_button2);
        nextButton.setOnClickListener(this);
        photoAttachButton = (Button) findViewById(R.id.pro_dev_photo_button);
        photoAttachButton.setOnClickListener(this);
        //editTextDateOfBirth = (EditText) findViewById(R.id.editText8);
        editTextDocumentationNo = (EditText) findViewById(R.id.textDocumentNo);
        editTextIssuedBy = (EditText) findViewById(R.id.textIssuedBy);
        editTextIssueDate = (EditText) findViewById(R.id.textIssueDate);
        editTextRegistrationNo = (EditText) findViewById(R.id.textRegistrationNo);
        editTextQualification = (EditText) findViewById(R.id.textQualification);
        editTextSpecialization = (EditText) findViewById(R.id.textSpecification);
        editTextForeignLanguage = (EditText) findViewById(R.id.textForeignLanguage);

        Spinner appDropdown = (Spinner) findViewById(R.id.pro_dev_edu_spinner);
        appDropdown.setPrompt("Select One");
        final String[] appItems = new String[]{"Education 1", "Education 2", "Education 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        appDropdown.setAdapter(adapter);
        appDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                educationLevel = appItems[position];
            }
        });

        // Load saved data
        Education education = FirebaseHelper.getInstance()
                .getApplication()
                .getProfessionalDevelopmentApplication()
                .getEducation();
        if (education != null) {
            int index = adapter.getPosition(education.getEducationLevel());
            if (index >= 0) {
                appDropdown.setSelection(index);
            }
            editTextForeignLanguage.setText(education.getForeignLanguage());
            SupportingDocumentation supportingDocumentation = education.getSupportingDocumentation();
            if (supportingDocumentation != null) {
                editTextDocumentationNo.setText(supportingDocumentation.getDocumentNumber());
                editTextIssueDate.setText(supportingDocumentation.getIssueDate());
                editTextIssuedBy.setText(supportingDocumentation.getIssuedBy());
                editTextQualification.setText(supportingDocumentation.getQualification());
                editTextSpecialization.setText(supportingDocumentation.getSpecialization());
                editTextRegistrationNo.setText(supportingDocumentation.getRegistrationNumber());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_dev_button2:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ProDevEducationActivity.this, ProDevSubmitActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ProDevEducationActivity.this, task.getException());
                        }
                    }
                });
                break;

            case R.id.pro_dev_photo_button:
                /*   WORKS only turn on the camera
                Intent proDevPhotoIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(proDevPhotoIntent);
                */
                Intent proDevPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(proDevPhotoIntent, 1);


                break;
        }
    }

    // Testing new functionality
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        SupportingDocumentation documentation = SupportingDocumentation.builder()
                .documentNumber(editTextDocumentationNo.getText().toString().trim())
                .issueDate(editTextIssueDate.getText().toString().trim())
                .issuedBy(editTextIssuedBy.getText().toString().trim())
                .qualification(editTextQualification.getText().toString().trim())
                .specialization(editTextSpecialization.getText().toString().trim())
                .registrationNumber(editTextRegistrationNo.getText().toString().trim())
                .build();

        Education education = Education.builder()
                .educationLevel(educationLevel)
                .foreignLanguage(editTextForeignLanguage.getText().toString().trim())
                .supportingDocumentation(documentation)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getProfessionalDevelopmentApplication()
                .setEducation(education);
        return null;
    }
}
