package ru.buepl.mobile.application;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.ActivityChooserModel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TranscriptActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextDocumentNo;
    EditText editTextIssueBy;
    EditText editTextIssueDate;
    EditText editTextCompetitionTitle;
    EditText editTextDiplomaNo;
    Button nextButton;
    String transcriptFromAnotherInst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);

        editTextDocumentNo = (EditText)findViewById(R.id.transcript_doc_no);
        editTextIssueBy = (EditText)findViewById(R.id.transcript_issue_by);
        editTextIssueDate = (EditText)findViewById(R.id.transcript_issue_date);
        editTextCompetitionTitle = (EditText)findViewById(R.id.transcript_dip_completion_title);
        editTextDiplomaNo = (EditText)findViewById(R.id.transcript_diploma_no);

        nextButton = (Button)findViewById(R.id.transcript_next_button);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.transcript_next_button:

                String documentNoTxt = editTextDocumentNo.getText().toString();
                String issueBy = editTextIssueBy.getText().toString();
                String competitionTile = editTextCompetitionTitle.getText().toString();
                String diplomaNo = editTextDiplomaNo.getText().toString();
                String anotherTranscript = transcriptFromAnotherInst;

                // intent to next page

                Intent advancePlacementIntent = new Intent(this, AdvancePlacementActivity.class);
                startActivity(advancePlacementIntent);

                break;
        }

    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        //which button checked?
        switch (view.getId()){
            case R.id.transcript_button_yes:
                if(checked){
                    transcriptFromAnotherInst = "Yes";
                }
                break;

            case R.id.transcript_button_no:
                if(checked){
                    transcriptFromAnotherInst = "No";
                }
                break;
        }
    }

}
