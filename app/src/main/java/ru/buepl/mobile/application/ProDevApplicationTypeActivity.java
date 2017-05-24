package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.professional_development.ApplicationType;
import ru.buepl.mobile.application.util.Toaster;

public class ProDevApplicationTypeActivity extends LoggedInActivity implements View.OnClickListener {

    Button nextButton;
    String proDevApplicationFor = "";
    ApplicationType.Level level = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_application_type3);

        nextButton = (Button) findViewById(R.id.pro_dev_button);
        nextButton.setOnClickListener(this);

        Spinner appDropdown = (Spinner) findViewById(R.id.application_spinner);
        appDropdown.setPrompt("Select One");
        final String[] appItems = new String[]{"", "Program 1", "Program 2", "Program 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        appDropdown.setAdapter(adapter);
        appDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                proDevApplicationFor = appItems[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                proDevApplicationFor = "";
            }
        });

        Spinner levelDropdown = (Spinner) findViewById(R.id.level_spinner);
        levelDropdown.setPrompt("Select One");
        String[] levelItems = new String[]{"", "Level A (Beginner)", "Level B (Intermediate)", "Level C (Advance)"};
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levelItems);
        levelDropdown.setAdapter(levelAdapter);
        levelDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    level = ApplicationType.Level.values()[position - 1];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                level = null;
            }
        });

        // Load saved data
        ApplicationType applicationType = FirebaseHelper.getInstance()
                .getApplication()
                .getProfessionalDevelopmentApplication()
                .getApplicationType();
        if (applicationType != null) {
            ApplicationType.Level level = applicationType.getLevel();
            if (level != null) {
                levelDropdown.setSelection(level.ordinal() + 1);
            }
            int index = adapter.getPosition(applicationType.getCourse());
            if (index >= 0) {
                appDropdown.setSelection(index);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("application type", "here: " + v.getId() + "; " + R.id.pro_dev_button);
        switch (v.getId()) {
            case R.id.pro_dev_button:
                Log.d("application type", "there");
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ProDevApplicationTypeActivity.this, ProDevEducationActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ProDevApplicationTypeActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        ApplicationType applicationType = ApplicationType.builder()
                .course(proDevApplicationFor)
                .level(level)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getProfessionalDevelopmentApplication()
                .setApplicationType(applicationType);
        return application;
    }
}
