package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import lombok.Getter;
import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.shared.Identification;
import ru.buepl.mobile.application.util.Toaster;

public class IdentificationActivity extends LoggedInActivity implements View.OnClickListener {

    Button nextButton;

    EditText editTextDateOfBirth;
    EditText editTextPlaceOfBirth;
    EditText editTextCitizenship;
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        editTextDateOfBirth = (EditText) findViewById(R.id.editText8);
        editTextPlaceOfBirth = (EditText) findViewById(R.id.editText10);
        editTextCitizenship = (EditText) findViewById(R.id.editText11);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroup);

        nextButton = (Button) findViewById(R.id.button7);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button7:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(IdentificationActivity.this, ProgramSelectionActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(IdentificationActivity.this, task.getException());
                        }
                    }
                });
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Identification.Gender gender;
        switch (radioGroupGender.getCheckedRadioButtonId()) {
            case R.id.radioButton2:
                gender = Identification.Gender.FEMALE;
                break;
            case R.id.radioButton3:
                gender = Identification.Gender.MALE;
                break;
            case -1:
                gender = null;
                break;
            default:
                throw new AssertionError("Can't happen!!");
        }

        Identification identification = Identification.builder()
                .birthDate(editTextDateOfBirth.getText().toString().trim())
                .placeOfBirth(editTextPlaceOfBirth.getText().toString().trim())
                .citizenship(editTextCitizenship.getText().toString().trim())
                .gender(gender)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.setIdentification(identification);

        return application;
    }
}
