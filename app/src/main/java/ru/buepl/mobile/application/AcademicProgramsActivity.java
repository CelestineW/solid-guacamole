package ru.buepl.mobile.application;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.buepl.mobile.application.data.Application;

public class AcademicProgramsActivity extends LoggedInActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_programs);
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
