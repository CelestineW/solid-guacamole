package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;

    EditText editTextFirstName;
    EditText editTextMI;
    EditText editTextLastName;
    EditText editTextStreetAddress;
    EditText editTextCity;
    EditText editTextZipCode;
    EditText editTextCountry;
    EditText editTextPhoneNumber;

    EditText editTextEmail;
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextFirstName = (EditText) findViewById(R.id.editText13);
        editTextMI = (EditText) findViewById(R.id.editText15);
        editTextLastName = (EditText) findViewById(R.id.editText16);
        editTextStreetAddress = (EditText) findViewById(R.id.editText17);
        editTextCity = (EditText) findViewById(R.id.editText18);
        editTextZipCode = (EditText) findViewById(R.id.editText19);
        editTextCountry = (EditText) findViewById(R.id.editText20);
        editTextPhoneNumber = (EditText) findViewById(R.id.editText21);

        editTextEmail = (EditText) findViewById(R.id.editText5);
        editTextUsername = (EditText) findViewById(R.id.editText3);
        editTextPassword = (EditText) findViewById(R.id.editText4);
        editTextRetypePassword = (EditText) findViewById(R.id.editText6);

        submitButton = (Button) findViewById(R.id.button6);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button6:

                String firstNameTxt = editTextFirstName.getText().toString();
                String MITxt = editTextMI.getText().toString();
                String lastNameTxt = editTextLastName.getText().toString();
                String streetAddressTxt = editTextStreetAddress.getText().toString();
                String cityTxt = editTextCity.getText().toString();
                String zipCodeTxt = editTextZipCode.getText().toString();
                String countryTxt = editTextCountry.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();

                String emailTxt = editTextEmail.getText().toString();
                String usernameTxt = editTextUsername.getText().toString();
                String passwordTxt = editTextPassword.getText().toString();
                String retypePasswordTxt = editTextRetypePassword.getText().toString();

                Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
                startActivity(mainMenuIntent);

                break;



        }
    }
}
