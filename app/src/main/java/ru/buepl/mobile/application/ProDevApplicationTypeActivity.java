package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ProDevApplicationTypeActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button nextButton;
    String proDevApplicationFor;
    String proDevApplicationLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_application_type3);

        nextButton = (Button)findViewById(R.id.pro_dev_button);
        nextButton.setOnClickListener(this);

        Spinner appDropdown = (Spinner)findViewById(R.id.application_spinner);
        String[] appItems = new String[] {"select one", "program 1", "program 2", "program 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        appDropdown.setAdapter(adapter);

        Spinner levelDropdown = (Spinner)findViewById(R.id.level_spinner);
        String[] levelItems = new String[] {"Select One", "Level A (Beginner)", "Level B (Intermediate)", "Level C (Advance)"};
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levelItems);
        levelDropdown.setAdapter(levelAdapter);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.pro_dev_button:

                Intent proDevEducationIntent = new Intent(this,ProDevEducationActivity.class);
                startActivity(proDevEducationIntent);
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
