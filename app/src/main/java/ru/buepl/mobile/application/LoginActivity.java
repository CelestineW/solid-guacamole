package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button signin, createaccount, takeatour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = (Button) findViewById(R.id.button);
        createaccount = (Button) findViewById(R.id.button2);
        takeatour = (Button) findViewById(R.id.button3);

        signin.setOnClickListener(this);
        createaccount.setOnClickListener(this);
        takeatour.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:

                Intent signinIntent = new Intent(this, SignInActivity.class);
                startActivity(signinIntent);
                break;

            case R.id.button2:
                Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
                break;

            case R.id.button3:

                Intent tourIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(tourIntent);
                break;
        }

    }
}
