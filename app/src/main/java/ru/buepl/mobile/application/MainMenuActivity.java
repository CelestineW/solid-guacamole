package ru.buepl.mobile.application;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.buepl.mobile.application.data.Application;

public class MainMenuActivity extends LoggedInActivity implements View.OnClickListener {

    Button programButton, appButton, webButton, galleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        programButton = (Button) findViewById(R.id.main_menu_academic_programs);
        appButton = (Button) findViewById(R.id.main_menu_application);
        webButton = (Button) findViewById(R.id.main_menu_website);
        galleryButton = (Button) findViewById(R.id.main_menu_gallery);

        programButton.setOnClickListener(this);
        appButton.setOnClickListener(this);
        webButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.main_menu_academic_programs:

                Intent academicPrograms = new Intent(this, AcademicProgramsActivity.class);
                startActivity(academicPrograms);

                break;
            case R.id.main_menu_application:

                Intent infoIntent = new Intent(this, PersonalInfoActivity.class);
                startActivity(infoIntent);

                break;

            case R.id.main_menu_website:

                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://buepl.ru/"));
                startActivity(webIntent);
                break;

            case R.id.main_menu_gallery:
                 /*
                Intent galleryIntent = new Intent(this, GalleryActivity.class);
                startActivity(galleryIntent);
                 */
                break;

        }

    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
