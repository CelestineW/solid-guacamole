package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccountActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account2);

        submitButton = (Button) findViewById(R.id.button5);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button5:

                Intent programChoice = new Intent(this, MainMenuActivity.class);
                startActivity(programChoice);

                break;

        }
    }
}
