package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;

    EditText editTextEmail;
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account2);

        editTextEmail = (EditText) findViewById(R.id.editText5);
        editTextUsername = (EditText) findViewById(R.id.editText3);
        editTextPassword = (EditText) findViewById(R.id.editText4);
        editTextRetypePassword = (EditText) findViewById(R.id.editText6);

        submitButton = (Button) findViewById(R.id.button5);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button5:

                String emailTxt = editTextEmail.getText().toString();
                String usernameTxt = editTextUsername.getText().toString();
                String passwordTxt = editTextPassword.getText().toString();
                String retypePasswordTxt = editTextRetypePassword.getText().toString();

                Intent programChoice = new Intent(this, MainMenuActivity.class);
                startActivity(programChoice);

                break;

        }
    }
}
