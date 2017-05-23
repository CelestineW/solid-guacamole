package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.HigherEducationApplication;
import ru.buepl.mobile.application.data.ProfessionalDevelopmentApplication;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.util.Toaster;

public class ProgramSelectionActivity extends LoggedInActivity implements View.OnClickListener {
    private volatile Application.Type applicationType = Application.Type.NONE;

    Button higherEdu, professionalDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_selection);

        higherEdu = (Button) findViewById(R.id.higherEducation);
        professionalDev = (Button) findViewById(R.id.professional);

        higherEdu.setOnClickListener(this);
        professionalDev.setOnClickListener(this);
    }


    //  higherEducation and professionalDevelopment application page

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.higherEducation:
                applicationType = Application.Type.HIGHER_EDUCATION;
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ProgramSelectionActivity.this, HigherEdApplicationTypeActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ProgramSelectionActivity.this, task.getException());
                        }

                    }
                });
                break;

            case R.id.professional:
                applicationType = Application.Type.PROFESSIONAL_DEVELOPMENT;
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ProgramSelectionActivity.this, ProDevApplicationTypeActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ProgramSelectionActivity.this, task.getException());
                        }

                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Application application = FirebaseHelper.getInstance().getApplication();
        Application.Type type = applicationType;
        application.setType(type);

        if (type == Application.Type.HIGHER_EDUCATION
                && application.getHigherEducationApplication() == null) {
            application.setHigherEducationApplication(HigherEducationApplication.builder().build());
        } else if (type == Application.Type.PROFESSIONAL_DEVELOPMENT
                && application.getProfessionalDevelopmentApplication() == null) {
            application.setProfessionalDevelopmentApplication(ProfessionalDevelopmentApplication.builder().build());
        }

        return application;
    }
}
