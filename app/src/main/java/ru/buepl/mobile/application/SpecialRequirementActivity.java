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
import ru.buepl.mobile.application.data.higher_education.SpecialRequirements;
import ru.buepl.mobile.application.data.higher_education.TestSpecialRequirement;
import ru.buepl.mobile.application.util.Toaster;

public class SpecialRequirementActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextSpecialTestTitle1, editTextSpecialRequirement1;
    EditText editTextSpecialTestTitle2, editTextSpecialRequirement2;
    EditText editTextSpecialTestTitle3, editTextSpecialRequirement3;
    EditText editTextSpecialTestTitle4, editTextSpecialRequirement4;

    Button specialRequirementNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_requirement);

        editTextSpecialTestTitle1 = (EditText) findViewById(R.id.special_requirement_test_1_title);
        editTextSpecialRequirement1 = (EditText) findViewById(R.id.special_requirement_1);

        editTextSpecialTestTitle2 = (EditText) findViewById(R.id.special_requirement_test_2_title);
        editTextSpecialRequirement2 = (EditText) findViewById(R.id.special_requirement_2);

        editTextSpecialTestTitle3 = (EditText) findViewById(R.id.special_requirement_test_3_title);
        editTextSpecialRequirement3 = (EditText) findViewById(R.id.special_requirement_3);

        editTextSpecialTestTitle4 = (EditText) findViewById(R.id.special_requirement_test_4_title);
        editTextSpecialRequirement4 = (EditText) findViewById(R.id.special_requirement_4);

        specialRequirementNextButton = (Button) findViewById(R.id.special_requirement_next_button);
        specialRequirementNextButton.setOnClickListener(this);

        // Load saved data
        SpecialRequirements specialRequirements = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getSpecialRequirements();
        if (specialRequirements != null) {
            TestSpecialRequirement requirement1 = specialRequirements.getRequirement1();
            if (requirement1 != null) {
                editTextSpecialTestTitle1.setText(requirement1.getTitle());
                editTextSpecialRequirement1.setText(requirement1.getSpecialRequirements());
            }
            TestSpecialRequirement requirement2 = specialRequirements.getRequirement2();
            if (requirement2 != null) {
                editTextSpecialTestTitle2.setText(requirement2.getTitle());
                editTextSpecialRequirement2.setText(requirement2.getSpecialRequirements());
            }
            TestSpecialRequirement requirement3 = specialRequirements.getRequirement3();
            if (requirement3 != null) {
                editTextSpecialTestTitle3.setText(requirement3.getTitle());
                editTextSpecialRequirement3.setText(requirement3.getSpecialRequirements());
            }
            TestSpecialRequirement requirement4 = specialRequirements.getRequirement4();
            if (requirement4 != null) {
                editTextSpecialTestTitle4.setText(requirement4.getTitle());
                editTextSpecialRequirement4.setText(requirement4.getSpecialRequirements());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.special_requirement_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SpecialRequirementActivity.this, MainMenuActivity.class); // TODO: 5/23/17 change activity
                            startActivity(intent);
                        } else {
                            Toaster.toastException(SpecialRequirementActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        TestSpecialRequirement requirement1 = TestSpecialRequirement.builder()
                .title(editTextSpecialTestTitle1.getText().toString().trim())
                .specialRequirements(editTextSpecialRequirement1.getText().toString().trim())
                .build();
        TestSpecialRequirement requirement2 = TestSpecialRequirement.builder()
                .title(editTextSpecialTestTitle2.getText().toString().trim())
                .specialRequirements(editTextSpecialRequirement2.getText().toString().trim())
                .build();
        TestSpecialRequirement requirement3 = TestSpecialRequirement.builder()
                .title(editTextSpecialTestTitle3.getText().toString().trim())
                .specialRequirements(editTextSpecialRequirement3.getText().toString().trim())
                .build();
        TestSpecialRequirement requirement4 = TestSpecialRequirement.builder()
                .title(editTextSpecialTestTitle4.getText().toString().trim())
                .specialRequirements(editTextSpecialRequirement4.getText().toString().trim())
                .build();

        SpecialRequirements specialRequirements = SpecialRequirements.builder()
                .requirement1(requirement1)
                .requirement2(requirement2)
                .requirement3(requirement3)
                .requirement4(requirement4)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setSpecialRequirements(specialRequirements);
        return application;
    }
}
