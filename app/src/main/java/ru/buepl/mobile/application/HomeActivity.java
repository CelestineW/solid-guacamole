package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button signIn, createAccount, takeATour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        signIn = (Button) findViewById(R.id.home_login_button);
        createAccount = (Button) findViewById(R.id.home_create_account_button);
        takeATour = (Button) findViewById(R.id.home_tour_button);

        signIn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        takeATour.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home_login_button:

                Intent signInIntent = new Intent(this, SignInActivity.class);
                startActivity(signInIntent);
                break;

            case R.id.home_create_account_button:
                Intent createAccountIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
                break;

            case R.id.home_tour_button:

                Intent tourIntent = new Intent(this, CreateAccountActivity.class);
                startActivity(tourIntent);
                break;
        }

    }
}
