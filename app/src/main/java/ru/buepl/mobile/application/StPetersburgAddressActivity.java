package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StPetersburgAddressActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextStreetAddress;
    EditText editTextAptNo;
    EditText editTextCity;
    EditText editTextZipCode;

    Button stPetersburgNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_petersburg_address);

        editTextStreetAddress = (EditText)findViewById(R.id.stPetersburg_street_address);
        editTextAptNo = (EditText)findViewById(R.id.stPetersburg_Apt_no);
        editTextCity = (EditText)findViewById(R.id.stPetersburg_city);
        editTextZipCode = (EditText)findViewById(R.id.stPetersburg_zip_code);

        stPetersburgNextButton = (Button)findViewById(R.id.stPetersburg_next_button);
        stPetersburgNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.stPetersburg_next_button:

                String streetAddressTxt = editTextStreetAddress.getText().toString();
                String aptNoTxt = editTextAptNo.getText().toString();
                String cityTxt = editTextCity.getText().toString();
                String zipCodeTxt = editTextZipCode.getText().toString();

                Intent nationalAcceptanceTestIntent = new Intent(this, NationalAcceptanceTestActivity.class);
                startActivity(nationalAcceptanceTestIntent);

                break;
        }
    }
}
