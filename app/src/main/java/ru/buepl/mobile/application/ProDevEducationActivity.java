package ru.buepl.mobile.application;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProDevEducationActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button nextButton, photoAttachButton;
    EditText editTextDocumentationNo;
    EditText editTextIssuedBy;
    EditText editTextIssueDate;
    EditText editTextRegistrationNo;
    EditText editTextQualification;
    EditText editTextSpecialization;
    EditText editTextForeignLanguage;

    private ImageView imageview;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_education);

        nextButton = (Button)findViewById(R.id.pro_dev_button2);
        nextButton.setOnClickListener(this);
        photoAttachButton = (Button)findViewById(R.id.pro_dev_photo_button);
        photoAttachButton.setOnClickListener(this);
        //editTextDateOfBirth = (EditText) findViewById(R.id.editText8);
        editTextDocumentationNo = (EditText)findViewById(R.id.textDocumentNo);
        editTextIssuedBy = (EditText)findViewById(R.id.textIssuedBy);
        editTextIssueDate = (EditText)findViewById(R.id.textIssueDate);
        editTextRegistrationNo = (EditText)findViewById(R.id.textRegistrationNo);
        editTextQualification = (EditText)findViewById(R.id.textQualification);
        editTextSpecialization = (EditText)findViewById(R.id.textSpecification);
        editTextForeignLanguage = (EditText)findViewById(R.id.textForeignLanguage);

        Spinner appDropdown = (Spinner)findViewById(R.id.pro_dev_edu_spinner);
        appDropdown.setPrompt("Select One");
        String[] appItems = new String[] {"Select One", "Education 1", "Education 2", "Education 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, appItems);
        appDropdown.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.pro_dev_button2:

                Intent proDevSubmitIntent = new Intent(this,ProDevSubmitActivity.class);
                startActivity(proDevSubmitIntent);
                break;

            case R.id.pro_dev_photo_button:
                /*   WORKS only turn on the camera
                Intent proDevPhotoIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(proDevPhotoIntent);
                */
                Intent proDevPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(proDevPhotoIntent, 1);


                break;
        }
    }

    // Testing new functionality
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
