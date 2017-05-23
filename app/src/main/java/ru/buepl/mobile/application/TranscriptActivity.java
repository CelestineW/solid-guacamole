package ru.buepl.mobile.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.ScholasticCompetition;
import ru.buepl.mobile.application.data.higher_education.Transcript;
import ru.buepl.mobile.application.data.higher_education.TranscriptInfo;
import ru.buepl.mobile.application.util.Toaster;

public class TranscriptActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextDocumentNo;
    EditText editTextIssueBy;
    EditText editTextIssueDate;
    EditText editTextCompetitionTitle;
    EditText editTextDiplomaNo;
    Button nextButton;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);

        editTextDocumentNo = (EditText) findViewById(R.id.transcript_doc_no);
        editTextIssueBy = (EditText) findViewById(R.id.transcript_issue_by);
        editTextIssueDate = (EditText) findViewById(R.id.transcript_issue_date);
        editTextCompetitionTitle = (EditText) findViewById(R.id.transcript_dip_completion_title);
        editTextDiplomaNo = (EditText) findViewById(R.id.transcript_diploma_no);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        nextButton = (Button) findViewById(R.id.transcript_next_button);
        nextButton.setOnClickListener(this);

        // Load saved data
        Transcript transcript = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getTranscript();
        if (transcript != null) {
            Boolean hasTranscript = transcript.getHasTranscript();
            if (hasTranscript != null) {
                if (hasTranscript) {
                    radioGroup.check(R.id.transcript_button_yes);
                    TranscriptInfo info = transcript.getInfo();
                    if (info != null) {
                        editTextDocumentNo.setText(info.getDocumentNumber());
                        editTextIssueDate.setText(info.getIssueDate());
                        editTextIssueBy.setText(info.getIssuedBy());
                    }
                } else {
                    radioGroup.check(R.id.transcript_button_no);
                }
            }
            ScholasticCompetition competition = transcript.getScholasticCompetition();
            if (competition != null) {
                editTextCompetitionTitle.setText(competition.getCompetitionTitle());
                editTextDiplomaNo.setText(competition.getDiplomaNumber());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transcript_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(TranscriptActivity.this, AdvancePlacementActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(TranscriptActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Boolean hasTranscript;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.transcript_button_yes:
                hasTranscript = true;
                break;
            case R.id.transcript_button_no:
                hasTranscript = false;
                break;
            default:
                hasTranscript = null;
        }

        TranscriptInfo transcriptInfo = null;
        if (hasTranscript != null && hasTranscript) {
            transcriptInfo = TranscriptInfo.builder()
                    .documentNumber(editTextDocumentNo.getText().toString().trim())
                    .issueDate(editTextIssueDate.getText().toString().trim())
                    .issuedBy(editTextIssueBy.getText().toString().trim())
                    .build();
        }

        ScholasticCompetition scholasticCompetition = ScholasticCompetition.builder()
                .competitionTitle(editTextCompetitionTitle.getText().toString().trim())
                .diplomaNumber(editTextDiplomaNo.getText().toString().trim())
                .build();

        Transcript transcript = Transcript.builder()
                .hasTranscript(hasTranscript)
                .info(transcriptInfo)
                .scholasticCompetition(scholasticCompetition)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setTranscript(transcript);
        return application;
    }
}
