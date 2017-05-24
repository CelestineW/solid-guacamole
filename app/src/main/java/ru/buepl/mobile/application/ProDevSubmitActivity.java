package ru.buepl.mobile.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.ProfessionalDevelopmentApplication;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.validation.ValidationError;

import static ru.buepl.mobile.application.data.validation.Validation.aggregateActivityValidations;
import static ru.buepl.mobile.application.data.validation.Validation.validateForActivity;

public class ProDevSubmitActivity extends LoggedInActivity implements View.OnClickListener {

    Button proDevSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_dev_submit);

        proDevSubmitButton = (Button) findViewById(R.id.pro_dev_submit_button);
        proDevSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_dev_submit_button:
                if (validateApplication()) {
                    Intent intent = new Intent(this, ProDevThankyouActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean validateApplication() {
        Application application = FirebaseHelper.getInstance().getApplication();
        ProfessionalDevelopmentApplication proDevApplication = application.getProfessionalDevelopmentApplication();

        List<Pair<Class<? extends Activity>, List<ValidationError>>> errors = aggregateActivityValidations(
                validateForActivity(this, PersonalInfoActivity.class, application.getPersonalInfo()),
                validateForActivity(this, IdentificationActivity.class, application.getIdentification()),
                validateForActivity(this, ProDevApplicationTypeActivity.class, proDevApplication.getApplicationType()),
                validateForActivity(this, ProDevEducationActivity.class, proDevApplication.getEducation())
        );

        if (errors.isEmpty()) {
            return true;
        } else {
            Pair<Class<? extends Activity>, List<ValidationError>> firstError = errors.get(0);
            Toast.makeText(this, firstError.second.get(0).getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, firstError.first));
            return false;
        }
    }

    @Nullable
    @Override
    protected Application collectApplicationDataToSave() {
        return null;
    }
}
