package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.ApplicationType;
import ru.buepl.mobile.application.util.Toaster;

public class HigherEdApplicationTypeActivity extends LoggedInActivity
        implements View.OnClickListener {

    Button nextButton;
    RadioGroup radioGroup;

    Integer yearNumber = null;
    String choice1 = "";
    String choice2 = "";
    String choice3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_ed_application_type);

        Spinner yearDropdown = (Spinner) findViewById(R.id.higher_ed_application_spinner);
        yearDropdown.setPrompt("Select One");
        String[] appItems = new String[]{"Year 1", "Year 2", "Year 3", "Year 4", "Year 5", "Year 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        yearDropdown.setAdapter(adapter);
        yearDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearNumber = position + 1;
            }
        });


        final String[] choices = new String[]{"05.03.06 - Environmental Sciences", "37.03.01 - Psychology", "40.03.01 - Law", "54.03.01 - Design", "38.03.01 - Economics", "38.03.02 - Management", "52.05.01 - Performing Arts – Acting", "52.05.01 - Performing Arts – Musical Theater"};


        Spinner programDropdown_c1 = (Spinner) findViewById(R.id.higher_ed_choice1);
        //programDropdown_c1.setPrompt("Choice 1");
        ArrayAdapter<String> choice1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c1.setAdapter(choice1Adapter);
        programDropdown_c1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choice1 = choices[position];
            }
        });

        Spinner programDropdown_c2 = (Spinner) findViewById(R.id.higher_ed_choice2);
        //programDropdown_c2.setPrompt("Choice 2");
        ArrayAdapter<String> choice2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c2.setAdapter(choice2Adapter);
        programDropdown_c2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choice2 = choices[position];
            }
        });

        Spinner programDropdown_c3 = (Spinner) findViewById(R.id.higher_ed_choice3);
        //programDropdown_c3.setPrompt("Choice 3");
        ArrayAdapter<String> choice3Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c3.setAdapter(choice3Adapter);
        programDropdown_c3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choice3 = choices[position];
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup2);

        nextButton = (Button) findViewById(R.id.higher_ed_app_type_next_button);
        nextButton.setOnClickListener(this);

        // Load saved data
        ApplicationType applicationType = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getApplicationType();
        if (applicationType != null) {
            if (applicationType.getYearNumber() != null) {
                yearDropdown.setSelection(applicationType.getYearNumber() - 1);
            }
            if (applicationType.getCourseType() != null) {
                switch (applicationType.getCourseType()) {
                    case EVENING:
                        radioGroup.check(R.id.evening);
                        break;
                    case DAY:
                        radioGroup.check(R.id.day);
                        break;
                    case CORRESPONDENCE:
                        radioGroup.check(R.id.correspondence);
                        break;
                }
            }
            int index = choice1Adapter.getPosition(applicationType.getProgramChoice1());
            if (index >= 0) {
                programDropdown_c1.setSelection(index);
            }
            index = choice2Adapter.getPosition(applicationType.getProgramChoice2());
            if (index >= 0) {
                programDropdown_c2.setSelection(index);
            }
            index = choice3Adapter.getPosition(applicationType.getProgramChoice3());
            if (index >= 0) {
                programDropdown_c3.setSelection(index);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.higher_ed_app_type_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(HigherEdApplicationTypeActivity.this, AcademicInformationActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(HigherEdApplicationTypeActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        ApplicationType.CourseType courseType;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.evening:
                courseType = ApplicationType.CourseType.EVENING;
                break;
            case R.id.day:
                courseType = ApplicationType.CourseType.DAY;
                break;
            case R.id.correspondence:
                courseType = ApplicationType.CourseType.CORRESPONDENCE;
                break;
            default:
                courseType = null;
        }

        ApplicationType applicationType = ApplicationType.builder()
                .yearNumber(yearNumber)
                .courseType(courseType)
                .programChoice1(choice1)
                .programChoice2(choice2)
                .programChoice3(choice3)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setApplicationType(applicationType);
        return application;
    }
}
