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
import ru.buepl.mobile.application.util.Toaster;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    Button signIn;

    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = (EditText) findViewById(R.id.login_email);
        editTextPassword = (EditText) findViewById(R.id.login_password);

        signIn = (Button) findViewById(R.id.login_button);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_button:

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.email_is_empty), Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(this, getResources().getString(R.string.password_is_empty), Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseHelper helper = FirebaseHelper.getInstance();
                    helper.login(email, password,
                            new UserDataUpdateListener() {
                                @Override
                                public void onUpdate(@NonNull AccountInfo accountInfo, @NonNull Application application) {
                                    final Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(intent);
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
                }
                break;
        }
    }
}
