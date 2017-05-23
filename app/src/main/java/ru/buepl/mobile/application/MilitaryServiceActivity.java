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
import ru.buepl.mobile.application.data.higher_education.MilitaryService;
import ru.buepl.mobile.application.util.Toaster;

public class MilitaryServiceActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextDocNo;
    EditText editTextIssueDate;
    EditText editTextIssueFrom;

    Button militaryNextButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military_service);

        editTextDocNo = (EditText) findViewById(R.id.military_serv_doc_no);
        editTextIssueDate = (EditText) findViewById(R.id.military_serv_issue_date);
        editTextIssueFrom = (EditText) findViewById(R.id.military_serv_issue_from);
        radioGroup = (RadioGroup) findViewById(R.id.military_sev_radio_group);

        militaryNextButton = (Button) findViewById(R.id.miliatry_serv_next_button);
        militaryNextButton.setOnClickListener(this);

        // Load saved data
        MilitaryService militaryService = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getMilitaryService();
        if (militaryService != null) {
            Boolean eligible = militaryService.getEligible();
            if (eligible != null) {
                if (eligible) {
                    radioGroup.check(R.id.military_serv_radio_yes);
                } else {
                    radioGroup.check(R.id.military_serv_radio_no);
                }
            }
            editTextDocNo.setText(militaryService.getDocumentNumber());
            editTextIssueDate.setText(militaryService.getIssueDate());
            editTextIssueFrom.setText(militaryService.getIssuedBy());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miliatry_serv_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MilitaryServiceActivity.this, StPetersburgAddressActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(MilitaryServiceActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Boolean eligible;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.military_serv_radio_yes:
                eligible = true;
                break;
            case R.id.military_serv_radio_no:
                eligible = false;
                break;
            default:
                eligible = null;
        }

        MilitaryService militaryService = MilitaryService.builder()
                .eligible(eligible)
                .documentNumber(editTextDocNo.getText().toString().trim())
                .issueDate(editTextIssueDate.getText().toString().trim())
                .issuedBy(editTextIssueFrom.getText().toString().trim())
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setMilitaryService(militaryService);
        return application;
    }
}
