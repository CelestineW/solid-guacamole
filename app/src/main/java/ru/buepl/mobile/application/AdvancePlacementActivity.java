package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.higher_education.AdvancePlacement;
import ru.buepl.mobile.application.data.higher_education.AdvancePlacementDocument;
import ru.buepl.mobile.application.util.Toaster;

public class AdvancePlacementActivity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextDocTitle1, editTextDocTitle2;
    EditText editTextDocNo1, editTextDocNo2;
    EditText editTextIssueBy1, editTextIssueBy2;
    EditText editTextIssueDate1, editTextIssueDate2;

    Button advancePlaceNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_placement);

        editTextDocTitle1 = (EditText)findViewById(R.id.advance_plc_doc_title_1);
        editTextDocNo1 = (EditText)findViewById(R.id.advance_plc_doc_number_1);
        editTextIssueBy1 = (EditText)findViewById(R.id.advance_plc_issued_by_1);
        editTextIssueDate1 = (EditText)findViewById(R.id.advance_plc_issued_date_1);

        editTextDocTitle2 = (EditText)findViewById(R.id.advance_plc_doc_title_2);
        editTextDocNo2 = (EditText)findViewById(R.id.advance_plc_doc_number_2);
        editTextIssueBy2 = (EditText)findViewById(R.id.advance_plc_issued_by_2);
        editTextIssueDate2 = (EditText)findViewById(R.id.advance_plc_issued_date_2);

        advancePlaceNextButton = (Button)findViewById(R.id.advance_plc_next_button);
        advancePlaceNextButton.setOnClickListener(this);

        // Load saved data
        AdvancePlacement advancePlacement = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getAdvancePlacement();
        if (advancePlacement != null) {
            AdvancePlacementDocument document1 = advancePlacement.getDocument1();
            if (document1 != null) {
                editTextDocNo1.setText(document1.getDocumentNumber());
                editTextDocTitle1.setText(document1.getDocumentTitle());
                editTextIssueDate1.setText(document1.getIssuedDate());
                editTextIssueBy1.setText(document1.getIssuedBy());
            }

            AdvancePlacementDocument document2 = advancePlacement.getDocument2();
            if (document2 != null) {
                editTextDocNo2.setText(document2.getDocumentNumber());
                editTextDocTitle2.setText(document2.getDocumentTitle());
                editTextIssueDate2.setText(document2.getIssuedDate());
                editTextIssueBy2.setText(document2.getIssuedBy());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.advance_plc_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AdvancePlacementActivity.this, MilitaryServiceActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(AdvancePlacementActivity.this, task.getException());
                        }
                    }
                });
                break;
        }

    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        AdvancePlacementDocument document1 = AdvancePlacementDocument.builder()
                .documentNumber(editTextDocNo1.getText().toString().trim())
                .documentTitle(editTextDocTitle1.getText().toString().trim())
                .issuedBy(editTextIssueBy1.getText().toString().trim())
                .issuedDate(editTextIssueDate1.getText().toString().trim())
                .build();

        AdvancePlacementDocument document2 = AdvancePlacementDocument.builder()
                .documentNumber(editTextDocNo2.getText().toString().trim())
                .documentTitle(editTextDocTitle2.getText().toString().trim())
                .issuedBy(editTextIssueBy2.getText().toString().trim())
                .issuedDate(editTextIssueDate2.getText().toString().trim())
                .build();

        AdvancePlacement advancePlacement = AdvancePlacement.builder()
                .document1(document1)
                .document2(document2)
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setAdvancePlacement(advancePlacement);
        return application;
    }
}
