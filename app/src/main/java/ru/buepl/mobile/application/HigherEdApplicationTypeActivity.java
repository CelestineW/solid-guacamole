package ru.buepl.mobile.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HigherEdApplicationTypeActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_ed_application_type);




        Spinner yearDropdown = (Spinner)findViewById(R.id.higher_ed_application_spinner);
        yearDropdown.setPrompt("Select One");
        String[] appItems = new String[] {"Year 1", "Year 2", "Year 3", "Year 4", "Year 5", "Year 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        yearDropdown.setAdapter(adapter);


        String[] choices = new String[] {"05.03.06 - Environmental Sciences","37.03.01 - Psychology","40.03.01 - Law","54.03.01 - Design","38.03.01 - Economics","38.03.02 - Menegement","52.05.01 - Performing Arts – Acting","52.05.01 - Performing Arts – Musical Theater"};


        Spinner programDropdown_c1 = (Spinner) findViewById(R.id.higher_ed_choice1);
        //programDropdown_c1.setPrompt("Choice 1");
        ArrayAdapter<String> choice1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c1.setAdapter(choice1Adapter);

        Spinner programDropdown_c2 = (Spinner) findViewById(R.id.higher_ed_choice2);
        //programDropdown_c2.setPrompt("Choice 2");
        ArrayAdapter<String> choice2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c2.setAdapter(choice2Adapter);

        Spinner programDropdown_c3 = (Spinner) findViewById(R.id.higher_ed_choice3);
        //programDropdown_c3.setPrompt("Choice 3");
        ArrayAdapter<String> choice3Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        programDropdown_c3.setAdapter(choice3Adapter);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
