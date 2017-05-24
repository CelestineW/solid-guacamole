package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpecialRequirementActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextSpecialTestTitle1, editTextSpecialRequirement1;
    EditText editTextSpecialTestTitle2, editTextSpecialRequirement2;
    EditText editTextSpecialTestTitle3, editTextSpecialRequirement3;
    EditText editTextSpecialTestTitle4, editTextSpecialRequirement4;

    Button specialRequirementNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_requirement);

        editTextSpecialTestTitle1 = (EditText)findViewById(R.id.special_requirement_test_1_title);
        editTextSpecialRequirement1 = (EditText)findViewById(R.id.special_requirement_1);

        editTextSpecialTestTitle2 = (EditText)findViewById(R.id.special_requirement_test_2_title);
        editTextSpecialRequirement2 = (EditText)findViewById(R.id.special_requirement_2);

        editTextSpecialTestTitle3 = (EditText)findViewById(R.id.special_requirement_test_3_title);
        editTextSpecialRequirement3 = (EditText)findViewById(R.id.special_requirement_3);

        editTextSpecialTestTitle4 = (EditText)findViewById(R.id.special_requirement_test_4_title);
        editTextSpecialRequirement4 = (EditText)findViewById(R.id.special_requirement_4);

        specialRequirementNextButton = (Button)findViewById(R.id.special_requirement_next_button);
        specialRequirementNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.special_requirement_next_button:

                String specialTestTitle1Txt = editTextSpecialTestTitle1.getText().toString();
                String specialRequirement1Txt = editTextSpecialRequirement1.getText().toString();

                String specialTestTitle2Txt = editTextSpecialTestTitle2.getText().toString();
                String specialRequirement2Txt = editTextSpecialRequirement2.getText().toString();

                String specialTestTitle3Txt = editTextSpecialTestTitle3.getText().toString();
                String specialRequirement3Txt = editTextSpecialRequirement3.getText().toString();

                String specialTestTitle4Txt = editTextSpecialTestTitle4.getText().toString();
                String specialRequirement4Txt = editTextSpecialRequirement4.getText().toString();

                Intent main = new Intent(this, MainMenuActivity.class);
                startActivity(main);
                break;
        }
    }
}
