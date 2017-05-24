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

import ru.buepl.mobile.application.data.Address;
import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.util.Toaster;

public class ReturnAddressActivity extends LoggedInActivity implements View.OnClickListener{

    EditText editTextReturnAddrFullName;
    EditText editTextReturnAddrStreetAddress;
    EditText editTextReturnAddrAptNo;
    EditText editTextReturnAddrZipCode;
    EditText editTextReturnAddrPhoneNo;

    Button returnAddrNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_address);

        editTextReturnAddrFullName = (EditText)findViewById(R.id.return_address_full_name);
        editTextReturnAddrStreetAddress = (EditText)findViewById(R.id.return_address_street_address);
        editTextReturnAddrAptNo = (EditText)findViewById(R.id.return_address_apt_no);
        editTextReturnAddrZipCode = (EditText)findViewById(R.id.return_address_zip_code);
        editTextReturnAddrPhoneNo = (EditText)findViewById(R.id.return_address_phone_no);

        returnAddrNextButton = (Button)findViewById(R.id.return_address_next_button);
        returnAddrNextButton.setOnClickListener(this);

        // Load saved data
        Address returnAddress = FirebaseHelper.getInstance()
                .getApplication()
                .getHigherEducationApplication()
                .getReturnAddress();
        if (returnAddress != null) {
            editTextReturnAddrFullName.setText(returnAddress.getFullName());
            editTextReturnAddrStreetAddress.setText(returnAddress.getStreetAddress());
            editTextReturnAddrAptNo.setText(returnAddress.getApartmentNumber());
            editTextReturnAddrZipCode.setText(returnAddress.getZipCode());
            editTextReturnAddrPhoneNo.setText(returnAddress.getPhoneNumber());
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.return_address_next_button:
                saveApplicationData(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ReturnAddressActivity.this, Certification1Activity.class);
                            startActivity(intent);
                        } else {
                            Toaster.toastException(ReturnAddressActivity.this, task.getException());
                        }
                    }
                });
                break;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        Address returnAddress = Address.builder()
                .fullName(editTextReturnAddrFullName.getText().toString().trim())
                .streetAddress(editTextReturnAddrStreetAddress.getText().toString().trim())
                .apartmentNumber(editTextReturnAddrAptNo.getText().toString().trim())
                .zipCode(editTextReturnAddrZipCode.getText().toString().trim())
                .phoneNumber(editTextReturnAddrPhoneNo.getText().toString().trim())
                .build();

        Application application = FirebaseHelper.getInstance().getApplication();
        application.getHigherEducationApplication()
                .setReturnAddress(returnAddress);
        return application;
    }
}
