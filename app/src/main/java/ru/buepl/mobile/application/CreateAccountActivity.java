package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        nextButton = (Button) findViewById(R.id.button6);

        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button6:

                Intent nextCreate = new Intent(this, CreateAccountActivity2.class);
                startActivity(nextCreate);

                break;



        }
    }
}
