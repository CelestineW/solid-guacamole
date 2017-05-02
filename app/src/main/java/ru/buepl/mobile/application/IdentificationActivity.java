package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IdentificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        nextButton = (Button) findViewById(R.id.button7);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button7:
                Intent programIntent = new Intent(this, ProgramSelectionActivity.class);
                startActivity(programIntent);
        }
    }
}
