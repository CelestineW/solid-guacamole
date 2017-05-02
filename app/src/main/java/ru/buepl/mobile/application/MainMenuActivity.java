package ru.buepl.mobile.application;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.buepl.mobile.application.data.shared.PersonalInfo;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button programButton, appButton, webButton, galleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        programButton = (Button) findViewById(R.id.b_academicProgs);
        appButton = (Button) findViewById(R.id.b_app);
        webButton = (Button) findViewById(R.id.b_mainWeb);
        galleryButton = (Button) findViewById(R.id.b_gallery);

        programButton.setOnClickListener(this);
        appButton.setOnClickListener(this);
        webButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.b_academicProgs:

                /*
                Intent academicPrograms = new Intent(this, AcademicActivity.class);
                startActivity(academicPrograms);
                */

                break;
            case R.id.b_app:

                Intent infoIntent = new Intent(this, PersonalInfoActivity.class);
                startActivity(infoIntent);

                break;

            case R.id.b_mainWeb:

                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://buepl.ru/"));
                startActivity(webIntent);
                break;

            case R.id.b_gallery:
                 /*
                Intent galleryIntent = new Intent(this, GalleryActivity.class);
                startActivity(galleryIntent);
                 */
                break;

        }

    }
}
