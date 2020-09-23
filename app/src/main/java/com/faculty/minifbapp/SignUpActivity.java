package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText etName, etEmail, etPassword;
    private TextView tvOpenLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvOpenLogin = findViewById(R.id.tv_open_sign_up);

        tvOpenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(signUpIntent);
            }
        });
        btnSignUp = findViewById(R.id.btn_login);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if user name, email not empty
                //Save data into shared pref
                //open main activity
                if (etName.getText() != null && etEmail.getText() != null) {
                    saveUserDataIntoCache(etEmail.getText().toString(), etName.getText().toString());
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void saveUserDataIntoCache(String email, String name) {
        PreferenceManager
                .getDefaultSharedPreferences(SignUpActivity.this)
                .edit()
                .putString("email", email)
                .putString("name", name)
                .apply();
    }
}