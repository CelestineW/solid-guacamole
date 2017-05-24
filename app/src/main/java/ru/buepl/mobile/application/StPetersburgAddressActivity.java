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
import ru.buepl.mobile.application.data.higher_education.StPetersburgAddress;
import ru.buepl.mobile.application.util.Toaster;

public class StPetersburgAddressActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextStreetAddress;
    EditText editTextAptNo;
    EditText editTextCity;
    EditText editTextZipCode;

    Button stPetersburgNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_petersburg_address);

        editTextStreetAddress = (EditText)findViewById(R.id.stPetersburg_street_address);
        editTextAptNo = (EditText)findViewById(R.id.stPetersburg_Apt_no);
        editTextCity = (EditText)findViewById(R.id.stPetersburg_city);
        editTextZipCode = (EditText)findViewById(R.id.stPetersburg_zip_code);

        stPetersburgNextButton = (Button)findViewById(R.id.stPetersburg_next_button);
        stPetersburgNextButton.setOnClickListener(this);

        // Load saved data
        StPetersburgAddress address = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getStPetersburgAddress();
        if (address != null) {
            editTextStreetAddress.setText(address.getStreetAddress());
            editTextAptNo.setText(address.getApartmentNumber());
            editTextCity.setText(address.getCity());
            editTextZipCode.setText(address.getZipCode());
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.stPetersburg_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(StPetersburgAddressActivity.this, NationalAcceptanceTestActivity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(StPetersburgAddressActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        StPetersburgAddress address = StPetersburgAddress.builder()
                .streetAddress(editTextStreetAddress.getText().toString().trim())
                .apartmentNumber(editTextAptNo.getText().toString().trim())
                .city(editTextCity.getText().toString().trim())
                .zipCode(editTextZipCode.getText().toString().trim())
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setStPetersburgAddress(address);
        return application;
    }
}
