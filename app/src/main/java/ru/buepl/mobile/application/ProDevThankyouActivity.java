package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProDevThankyouActivity extends AppCompatActivity implements View.OnClickListener{

    Button proDevMainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_thankyou);

        proDevMainMenuButton = (Button)findViewById(R.id.pro_dev_main_menu_button);
        proDevMainMenuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.pro_dev_main_menu_button:

                Intent proDevMainMenuIntent = new Intent(this, MainMenuActivity.class);
                startActivity(proDevMainMenuIntent);
        }

    }
}
