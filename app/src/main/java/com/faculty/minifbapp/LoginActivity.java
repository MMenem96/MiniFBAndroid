package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText etEmail, etPassword;
    private TextView tvOpenSignUp;
    private String email = "mmoniemfahmy@gmail.com", password = "123456";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvOpenSignUp = findViewById(R.id.tv_open_sign_up);
        tvOpenSignUp.setOnClickListener(this);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //Start login process
                startLoginProcess();
                break;
            case R.id.tv_open_sign_up:
                //Open Sign Up Activity
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }

    private void startLoginProcess() {
        if (etEmail.getText().toString().equals(email) && etPassword.getText().toString().equals(password)) {
            saveUserDataIntoCache(email, "Mohamed Abdelmoniem");
            startMainActivity();
        } else {
            Toast.makeText(LoginActivity.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isUserLoggedIn()) {
            startMainActivity();
        }
    }

    private boolean isUserLoggedIn() {
        if (PreferenceManager.getDefaultSharedPreferences(this).getString("email", "").equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void saveUserDataIntoCache(String email, String name) {
        PreferenceManager
                .getDefaultSharedPreferences(LoginActivity.this)
                .edit()
                .putString("email", email)
                .putString("name", name)
                .apply();
    }
}