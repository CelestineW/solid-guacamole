package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.buepl.mobile.application.data.shared.Identification;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        nextButton = (Button) findViewById(R.id.button8);

        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button8:
                Intent identificationIntent = new Intent(this, IdentificationActivity.class);
                startActivity(identificationIntent);

        }
    }
}
