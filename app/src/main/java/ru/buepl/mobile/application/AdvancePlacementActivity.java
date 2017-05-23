package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdvancePlacementActivity extends AppCompatActivity implements View.OnClickListener{

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
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.advance_plc_next_button:

                String docTitle1Txt = editTextDocTitle1.getText().toString();
                String docNo1Txt = editTextDocNo1.getText().toString();
                String issueBy1Txt = editTextIssueBy1.getText().toString();
                String issueDate1Txt = editTextIssueDate1.getText().toString();

                String docTitle2Txt = editTextDocTitle2.getText().toString();
                String docNo2Txt = editTextDocNo2.getText().toString();
                String issueBy2Txt = editTextIssueBy2.getText().toString();
                String issueDate2Txt = editTextIssueDate2.getText().toString();

                Intent militaryIntent = new Intent(this, MilitaryServiceActivity.class);
                startActivity(militaryIntent);
                break;
        }

    }
}
