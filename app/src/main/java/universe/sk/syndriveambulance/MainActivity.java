package universe.sk.syndriveambulance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import universe.sk.syndriveambulance.Model.User;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    RelativeLayout rootLayout;
    String name, email, password, phone, confirmpass;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Before setContentView
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                                .setDefaultFontPath("fonts/monaco.ttf")
                                                .setFontAttrId(R.attr.fontPath)
                                                .build());
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        setupUIViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });

    } // end of onCreate

    private void showRegisterDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER ");
        // dialog.setMessage("Please use Email to register");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.layout_register, null);

        final MaterialEditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
        etName = register_layout.findViewById(R.id.etName);
        etEmail = register_layout.findViewById(R.id.etEmail);
        etPhone = register_layout.findViewById(R.id.etPhone);
        etPassword = register_layout.findViewById(R.id.etPassword);
        etConfirmPassword = register_layout.findViewById(R.id.etConfirmPassword);

        dialog.setView(register_layout);
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmpass = etConfirmPassword.getText().toString().trim();
        phone = etPhone.getText().toString().trim();

        //Set buttons
        dialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));

                // Check validation
//                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
//                    Snackbar.make(rootLayout, "Please enter your Name", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
//                    Snackbar.make(rootLayout, "Please enter your Email Address", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
//                    Snackbar.make(rootLayout, "Please enter your Phone Number", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
//                    Snackbar.make(rootLayout, "Please enter your Password", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etConfirmPassword.getText().toString().trim()) ) {
//                    Snackbar.make(rootLayout, "Please confirm your Password", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                //if (password.length() < 8) {
//                //   Snackbar.make(rootLayout, "Min 8 char required in password", Snackbar.LENGTH_SHORT).show();
//                //   return;
//                //}
//                if (!password.equals(confirmpass)) {
//                    Snackbar.make(rootLayout, "Confirm password doesn't match Password", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }

                //Register new User
//                auth.createUserWithEmailAndPassword(email, password)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                //Save user to db
//                                User user = new User();
//                                user.setEmail(email);
//                                user.setName(name);
//                                user.setPassword(password);
//                                user.setPhone(phone);
//
//                                // use email as key
//                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                        .setValue(user)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Snackbar.make(rootLayout, "Registered successfully", Snackbar.LENGTH_SHORT).show();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
//                                            }
//                                        });
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    } // end of showRegisterDialog

    private void showLoginDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("LOGIN ");
        //dialog.setMessage("Please sign-in using Email");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_login, null);

        final MaterialEditText etEmail, etPassword;
        etEmail = login_layout.findViewById(R.id.etEmail);
        etPassword = login_layout.findViewById(R.id.etPassword);

        dialog.setView(login_layout);

        final String email, password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        //Set buttons
        dialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                // Disable SignIn Button if processing
                btnLogin.setEnabled(false);

                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));

                // Check validation
//                if (TextUtils.isEmpty(email)) {
//                    Snackbar.make(rootLayout, "Please enter your Email Address", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(password)) {
//                    Snackbar.make(rootLayout, "Please enter your Password", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }

                //final AlertDialog waitingDialog = new SpotsDialog(MainActivity.this);
                //waitingDialog.show();

                // Login
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                waitingDialog.dismiss();
//                                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
//                                finish();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                waitingDialog.dismiss();
//                                Snackbar.make(rootLayout, "Failed " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
//                         //Activate SignIn Button
//                          btnLogin.setEnabled(true);
//                            }
//                        });
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    } // end of showLoginDialog

    private void setupUIViews() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        rootLayout = findViewById(R.id.rootLayout);
    }

} // end of MainActivity
