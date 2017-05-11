package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import ru.buepl.mobile.application.data.AccountInfo;
import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.firebase.UserDataUpdateListener;
import ru.buepl.mobile.application.data.validation.Validation;
import ru.buepl.mobile.application.util.Toaster;

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
    EditText editTextPassword;
    EditText editTextRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextFirstName = (EditText) findViewById(R.id.create_account_first_name);
        editTextMI = (EditText) findViewById(R.id.create_account_middle_initial);
        editTextLastName = (EditText) findViewById(R.id.create_account_last_name);
        editTextStreetAddress = (EditText) findViewById(R.id.create_account_street_address);
        editTextCity = (EditText) findViewById(R.id.create_account_city);
        editTextZipCode = (EditText) findViewById(R.id.create_account_zip_code);
        editTextCountry = (EditText) findViewById(R.id.create_account_counrty);
        editTextPhoneNumber = (EditText) findViewById(R.id.create_account_phone_number);

        editTextEmail = (EditText) findViewById(R.id.create_account_email);
        editTextPassword = (EditText) findViewById(R.id.create_account_password);
        editTextRetypePassword = (EditText) findViewById(R.id.create_account_retype_password);

        submitButton = (Button) findViewById(R.id.create_account_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.create_account_button:

                String emailTxt = editTextEmail.getText().toString().trim();
                String passwordTxt = editTextPassword.getText().toString();
                String retypePasswordTxt = editTextRetypePassword.getText().toString();

                AccountInfo accountInfo = AccountInfo.builder()
                        .firstName(editTextFirstName.getText().toString().trim())
                        .middleInitial(editTextMI.getText().toString().trim())
                        .lastName(editTextLastName.getText().toString().trim())
                        .address(editTextStreetAddress.getText().toString().trim())
                        .city(editTextCity.getText().toString().trim())
                        .zipCode(editTextZipCode.getText().toString().trim())
                        .country(editTextCountry.getText().toString().trim())
                        .phone(editTextPhoneNumber.getText().toString().trim())
                        .email(emailTxt)
                        .build();

                if (Validation.validateAndToastFirstError(accountInfo, this)) {
                    if (!passwordTxt.equals(retypePasswordTxt)) {
                        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseHelper.getInstance().createAccount(emailTxt, passwordTxt, accountInfo,
                                new UserDataUpdateListener() {
                                    @Override
                                    public void onUpdate(@NonNull AccountInfo accountInfo, @NonNull Application application) {
                                        final Intent mainMenuIntent = new Intent(CreateAccountActivity.this, MainMenuActivity.class);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(mainMenuIntent);
                                                finish();
                                            }
                                        });
                                    }
                                },
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toaster.toastException(CreateAccountActivity.this, task.getException());
                                    }
                                });
                    }
                }
                break;
        }
    }
}
