package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MilitaryServiceActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextDocNo;
    EditText editTextIssueDate;
    EditText editTextIssueFrom;

    Button militaryNextButton;

    String militaryServiceEligibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military_service);

        editTextDocNo = (EditText)findViewById(R.id.military_serv_doc_no);
        editTextIssueDate = (EditText)findViewById(R.id.military_serv_issue_date);
        editTextIssueFrom = (EditText)findViewById(R.id.military_serv_issue_from);

        militaryNextButton = (Button)findViewById(R.id.miliatry_serv_next_button);
        militaryNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miliatry_serv_next_button:

                String docNoTxt = editTextDocNo.getText().toString();
                String issueDateTxt = editTextIssueDate.getText().toString();
                String issueFromTxt = editTextIssueFrom.getText().toString();
                String militaryServiceTxt = militaryServiceEligibility;

                Intent StPetersburgAddressIntent = new Intent(this, StPetersburgAddressActivity.class);
                startActivity(StPetersburgAddressIntent);

                break;
        }
    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        //which button checked?
        switch (view.getId()){
            case R.id.military_serv_radio_yes:
                if(checked){
                    militaryServiceEligibility = "Yes";
                }
                break;

            case R.id.military_serv_radio_no:
                if(checked){
                    militaryServiceEligibility = "No";
                }
                break;
        }
    }

}
