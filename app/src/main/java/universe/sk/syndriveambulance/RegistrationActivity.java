package universe.sk.syndriveambulance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import universe.sk.syndriveambulance.Model.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvExist;
    private FirebaseAuth firebaseAuth;
    String name, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email = etEmail.getText().toString().trim();
                    String user_password = etPassword.getText().toString().trim();

                    //store in firebase
                }
            }
        });

    }

    private void setupUIViews() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
    }

    private boolean validate() {
        Boolean result = false;
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        String confirmPass = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty())
        {
            Toast.makeText(this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
        }
        else
        {   if (password.equals(confirmPass))
                result = true;
            else
                Toast.makeText(this, "Confirm password doesn't match with your password!", Toast.LENGTH_SHORT).show();
        }
        return result;
    } //end of isValidate()

    private void addUser(){
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseusers= firebaseDatabase.getReference(firebaseAuth.getUid());
//        Userinfo user;
//        user = new Userinfo(name,email,date,bloodgrp,emname1,emnum1,emname2,emnum2,emname3,emnum3);
//        databaseusers.setValue(user);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseusers = firebaseDatabase.getReference("users");
        User user = new User(email, name, phone);
//        user.setName(name);
//        user.setEmail(email);
//        user.setPhone(phone);
        databaseusers.child(firebaseAuth.getUid()).setValue(user);

    }
}
