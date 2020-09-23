package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvWelcome;
    private String email, name;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getUserDetailsFromCache();
        populateViews();
    }

    private void populateViews() {
        tvWelcome.setText("Welcome " + name + "\n" + "Your email is " + email);
    }

    private void getUserDetailsFromCache() {
        email = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("email", "");
        name = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("name", "");
    }

    private void initViews() {
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
        tvWelcome = findViewById(R.id.tv_welcome);
    }

    private void openLoginActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void clearCache() {
        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().clear().apply();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_logout) {//Clear Shared Prefs
            //Open LoginActivity
            //Finish MainActivity
            clearCache();
            openLoginActivity();
            finish();
        }
    }
}