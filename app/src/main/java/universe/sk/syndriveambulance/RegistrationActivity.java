package universe.sk.syndriveambulance;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etName, etEmailsign, etPassign, etConfirmPassign, etPhone, etDate;
    TextView tvExist;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SIGN UP");
        setupUIViews();

        tvExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    } // end of onCreate

    private void setupUIViews() {
        etName = findViewById(R.id.etName);
        etEmailsign = findViewById(R.id.etEmailsign);
        etPassign = findViewById(R.id.etPassign);
        etConfirmPassign = findViewById(R.id.etConfirmPassign);
        etDate = findViewById(R.id.etDate);
        etPhone = findViewById(R.id.etPhone);
        tvExist = findViewById(R.id.tvExist);
        btn_register = findViewById(R.id.btn_register);
    }

} // end of RegistrationActivity
