package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReturnAddressActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextReturnAddrFullName;
    EditText editTextReturnAddrStreetAddress;
    EditText editTextReturnAddrAptNo;
    EditText editTextReturnAddrZipCode;
    EditText editTextReturnAddrPhoneNo;

    Button returnAddrNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_address);

        editTextReturnAddrFullName = (EditText)findViewById(R.id.return_address_full_name);
        editTextReturnAddrStreetAddress = (EditText)findViewById(R.id.return_address_street_address);
        editTextReturnAddrAptNo = (EditText)findViewById(R.id.return_address_apt_no);
        editTextReturnAddrZipCode = (EditText)findViewById(R.id.return_address_zip_code);
        editTextReturnAddrPhoneNo = (EditText)findViewById(R.id.return_address_phone_no);

        returnAddrNextButton = (Button)findViewById(R.id.return_address_next_button);
        returnAddrNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.return_address_next_button:

                String returnAddrFullNameTxt = editTextReturnAddrFullName.getText().toString();
                String returnAddrStreedAddress = editTextReturnAddrStreetAddress.getText().toString();
                String returnAddrAptNo = editTextReturnAddrAptNo.getText().toString();
                String returnAddrZipCode = editTextReturnAddrZipCode.getText().toString();
                String returnAddrPhoneNo = editTextReturnAddrPhoneNo.getText().toString();

                Intent certification1Intent = new Intent(this, Certification1Activity.class);
                startActivity(certification1Intent);

                break;
        }
    }
}
