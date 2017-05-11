package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProDevSubmitActivity extends AppCompatActivity implements View.OnClickListener{

    Button proDevSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_submit);

        proDevSubmitButton = (Button)findViewById(R.id.pro_dev_submit_button);
        proDevSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.pro_dev_submit_button:

                Intent proDevThankyouIntent = new Intent(this, ProDevThankyouActivity.class);
                startActivity(proDevThankyouIntent);

                break;
        }

    }
}
