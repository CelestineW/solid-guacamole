package ru.buepl.mobile.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.HigherEducationApplication;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.data.validation.Validatable;
import ru.buepl.mobile.application.data.validation.ValidationError;

import static ru.buepl.mobile.application.data.validation.Validation.*;

public class ApplicantSignatureActivity extends LoggedInActivity implements View.OnClickListener {

    EditText editTextApplicantSign;
    EditText editTextApplicantSignDate;
    EditText editTextApplicantRepresentSign;
    EditText editTextApplicantRepresentSignDate;

    Button applicantSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_signature);

        editTextApplicantSign = (EditText) findViewById(R.id.applicant_sign);
        editTextApplicantSignDate = (EditText) findViewById(R.id.applicant_sign_date_1);
        editTextApplicantRepresentSign = (EditText) findViewById(R.id.applicant_represent_sign);
        editTextApplicantRepresentSignDate = (EditText) findViewById(R.id.applicant_sign_date_2);

        applicantSubmitButton = (Button) findViewById(R.id.applicant_sign_submit_button);
        applicantSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.applicant_sign_submit_button:

                String applicantSignTxt = editTextApplicantSign.getText().toString();
                String applicantSignDateTxt = editTextApplicantRepresentSignDate.getText().toString();
                String applicantRepresentSignTxt = editTextApplicantRepresentSign.getText().toString();
                String applicantRepresentSignDateTxt = editTextApplicantRepresentSignDate.getText().toString();

                if (validateApplication()) {
                    startActivity(new Intent(this, ProDevThankyouActivity.class));
                }
                break;
        }
    }

    private boolean validateApplication() {
        Application application = FirebaseHelper.getInstance().getApplication();
        final HigherEducationApplication higherEdApplication = application.getHigherEducationApplication();

        List<Pair<Class<? extends Activity>, List<ValidationError>>> errors = aggregateActivityValidations(
                validateForActivity(this, PersonalInfoActivity.class, application.getPersonalInfo()),
                validateForActivity(this, IdentificationActivity.class, application.getIdentification()),
                validateForActivity(this, HigherEdApplicationTypeActivity.class, higherEdApplication.getApplicationType()),
                validateForActivity(this, AcademicInformationActivity.class, higherEdApplication.getAcademicInfo()),
                validateForActivity(this, TranscriptActivity.class, higherEdApplication.getTranscript()),
                validateForActivity(this, AdvancePlacementActivity.class, higherEdApplication.getAdvancePlacement()),
                validateForActivity(this, MilitaryServiceActivity.class, higherEdApplication.getMilitaryService()),
                validateForActivity(this, StPetersburgAddressActivity.class, higherEdApplication.getStPetersburgAddress()),
                validateForActivity(this, NationalAcceptanceTestActivity.class, higherEdApplication.getNationalAcceptanceTest()),
                validateForActivity(this, UniversityEntranceExamsActivity.class, higherEdApplication.getUniversityEntranceExams()),
                validateForActivity(this, ParentsInformationActivity.class, higherEdApplication.getParentsInfo()),
                validateForActivity(this, SpecialRequirementActivity.class, higherEdApplication.getSpecialRequirements()),
                validateForActivity(this, DormitoryActivity.class, new Validatable() {
                    @NonNull
                    @Override
                    public List<ValidationError> validate(@NonNull Context context) {
                        return nonNull(higherEdApplication.getDormitory(), R.string.dormitory, context);
                    }
                }),
                validateForActivity(this, ReturnAddressActivity.class, higherEdApplication.getReturnAddress())
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
