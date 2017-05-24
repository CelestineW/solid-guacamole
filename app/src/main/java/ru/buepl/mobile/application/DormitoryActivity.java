package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.util.Toaster;

public class DormitoryActivity extends LoggedInActivity implements View.OnClickListener {

    Button dormitoryNextButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory);

        radioGroup = (RadioGroup) findViewById(R.id.dormitory_radio_group);

        dormitoryNextButton = (Button)findViewById(R.id.dormitory_next_button);
        dormitoryNextButton.setOnClickListener(this);

        // Load saved data
        Boolean dormitory = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getDormitory();
        if (dormitory != null) {
            if (dormitory) {
                radioGroup.check(R.id.dormitory_radio_yes);
            } else {
                radioGroup.check(R.id.dormitory_radio_no);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.dormitory_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(DormitoryActivity.this, ReturnAddressActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(DormitoryActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Boolean dormitory;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.dormitory_radio_yes:
                dormitory = true;
                break;
            case R.id.dormitory_radio_no:
                dormitory = false;
                break;
            default:
                dormitory = null;
        }

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setDormitory(dormitory);
        return application;
    }
}
