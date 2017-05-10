package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.buepl.mobile.application.data.HigherEducationApplication;

public class ProgramSelectionActivity extends AppCompatActivity implements View.OnClickListener{

    Button higherEdu, professionalDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_selection);

        higherEdu = (Button)findViewById(R.id.higherEducation);
        professionalDev = (Button) findViewById(R.id.professional);

        higherEdu.setOnClickListener(this);
        professionalDev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    /*  higherEducation and professionalDevelopment application page

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.higherEducation:
                Intent higherEducationApplicationIntent = new Intent(this, .class);
                startActivity(higherEducationApplicationIntent);
                break;

            case R.id.professional:
                Intent professionalDevelopmentIntent = new Intent(this, .class);
                startActivity(professionalDevelopmentIntent);

                break;
        }
    }

    */

}
