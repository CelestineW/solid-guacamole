package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class DormitoryActivity extends AppCompatActivity implements View.OnClickListener {

    String dormitory;
    Button dormitoryNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory);

        dormitoryNextButton = (Button)findViewById(R.id.dormitory_next_button);
        dormitoryNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.dormitory_next_button:

                String dormitoryText = dormitory;

                Intent returnAddressIntent = new Intent(this, ReturnAddressActivity.class);
                startActivity(returnAddressIntent);
        }
    }

    public void onRadioButtonClicked(View view){
        //button checked?
        boolean checked = ((RadioButton) view).isChecked();

        //which button checked?
        switch (view.getId()){
            case R.id.dormitory_radio_yes:
                if(checked){
                    dormitory = "Yes";
                }
                break;
            case R.id.dormitory_radio_no:
                if(checked){
                    dormitory = "No";
                }
                break;
        }
    }

}
