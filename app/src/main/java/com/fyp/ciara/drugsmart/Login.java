package com.fyp.ciara.drugsmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/


public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;
    private ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnLogin = (Button) findViewById(R.id.sign_in_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (!task.isSuccessful()) {
                                        String temp = "auth failed";
                                        try {
                                            temp =  task.getException().getMessage();
                                        } catch (Exception e) {

                                        }
                                        Toast.makeText(Login.this, temp,
                                                Toast.LENGTH_SHORT).show();


                                    }

//                                        if (!task.isSuccessful()) {
//                                            try {
//                                                throw task.getException();
//                                            } catch(FirebaseAuthWeakPasswordException e) {
//                                                inputPassword.setError("Weak Password");
//                                                inputPassword.requestFocus();
//                                            } catch(FirebaseAuthInvalidCredentialsException e) {
//                                                inputEmail.setError("Invalid Email");
//                                                inputEmail.requestFocus();
//                                            } catch(FirebaseAuthUserCollisionException e) {
//                                                inputEmail.setError("User already exists");
//                                                inputEmail.requestFocus();
//                                            } catch(Exception e) {
//                                                Log.e("Sign Up Failed", e.getMessage());
//                                            }

//                                            Toast.makeText(
//                                                    Login.this,
//                                                    "Authentication Failed",
//                                                    Toast.LENGTH_LONG).show();
//                                            Log.v("error", task.getResult().toString());
                                         else {
                                            Intent intent = new Intent(Login.this, WelcomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                Login.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.forget_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class).putExtra("Mode", 0));
            }
        });

    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, WelcomeActivity.class));
            finish();
        }
        super.onResume();
    }
}
