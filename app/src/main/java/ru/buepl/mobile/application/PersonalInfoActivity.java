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
import ru.buepl.mobile.application.data.shared.PassportInfo;
import ru.buepl.mobile.application.data.shared.PersonalInfo;
import ru.buepl.mobile.application.util.Toaster;

public class PersonalInfoActivity extends LoggedInActivity implements View.OnClickListener {

    Button nextButton;

    EditText editTextHomePhoneNumber;
    EditText editTextCellPhoneNumber;
    EditText editTextPassportNumber;
    EditText editTextIssueDate;
    EditText editTextIssuedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        editTextHomePhoneNumber = (EditText) findViewById(R.id.personal_info_home_phone);
        editTextCellPhoneNumber = (EditText) findViewById(R.id.personal_info_cell_phone);
        editTextPassportNumber = (EditText) findViewById(R.id.passport_number);
        editTextIssueDate = (EditText) findViewById(R.id.passport_issue_date);
        editTextIssuedBy = (EditText) findViewById(R.id.passport_issued_by);

        nextButton = (Button) findViewById(R.id.personal_info_next_button);
        nextButton.setOnClickListener(this);

        // Load application data
        PersonalInfo personalInfo = FirebaseHelper.getInstance()
                .getApplication()
                .getPersonalInfo();
        if (personalInfo != null) {
            editTextHomePhoneNumber.setText(personalInfo.getHomePhone());
            editTextCellPhoneNumber.setText(personalInfo.getCellPhone());

            PassportInfo passportInfo = personalInfo.getPassportInfo();
            if (passportInfo != null) {
                editTextPassportNumber.setText(passportInfo.getNumber());
                editTextIssueDate.setText(passportInfo.getIssueDate());
                editTextIssuedBy.setText(passportInfo.getIssuedBy());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.personal_info_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(PersonalInfoActivity.this, IdentificationActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(PersonalInfoActivity.this, task.getException());
                        }
                    }
                });
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        PassportInfo passportInfo = PassportInfo.builder()
                .number(editTextPassportNumber.getText().toString().trim())
                .issueDate(editTextIssueDate.getText().toString().trim())
                .issuedBy(editTextIssuedBy.getText().toString().trim())
                .build();
        PersonalInfo personalInfo = PersonalInfo.builder()
                .homePhone(editTextHomePhoneNumber.getText().toString().trim())
                .cellPhone(editTextCellPhoneNumber.getText().toString().trim())
                .passportInfo(passportInfo)
                .build();
        Application application = FirebaseHelper.getInstance().getApplication();
        application.setPersonalInfo(personalInfo);

        return application;
    }
}
