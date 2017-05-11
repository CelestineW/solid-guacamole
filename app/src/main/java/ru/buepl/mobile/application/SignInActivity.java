package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import ru.buepl.mobile.application.data.AccountInfo;
import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.firebase.UserDataUpdateListener;
import ru.buepl.mobile.application.util.Toaster;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    Button signIn;

    EditText editTextUsername;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextUsername = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);

        signIn = (Button) findViewById(R.id.button4);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button4:

                String usernameTxt = editTextUsername.getText().toString().trim();
                String passwordTxt = editTextPassword.getText().toString();

                FirebaseHelper helper = FirebaseHelper.getInstance();
                helper.login(usernameTxt, passwordTxt,
                        new UserDataUpdateListener() {
                            @Override
                            public void onUpdate(@NonNull AccountInfo accountInfo, @NonNull Application application) {
                                final Intent programChoice = new Intent(SignInActivity.this, MainMenuActivity.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(programChoice);
                                        finish();
                                    }
                                });
                            }
                        },
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toaster.toastException(SignInActivity.this, task.getException());
                            }
                        });

                break;
        }
    }
}
