package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvWelcome;
    private String email, name;
    private Button btnLogout;
    private RecyclerView rvPosts;
    private List<Post> posts = new ArrayList<>();
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
//        getUserDetailsFromCache();
        populateViews();
    }

    private void populateViews() {
//        tvWelcome.setText("Welcome " + name + "\n" + "Your email is " + email);
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
        rvPosts = findViewById(R.id.rv_posts);
        posts.add(new Post(1, "HBD Ya Moniem", "29/9/2020", new User("Sheko")));
        posts.add(new Post(2, "Our Second Post", "26/9/2020", new User("Youssef")));
        posts.add(new Post(3, "Our Third Post", "25/9/2020", new User("Ebrahim")));
        posts.add(new Post(4, "Our Fourth Post", "24/9/2020", new User("Mohamed")));
        posts.add(new Post(5, "Our Fifth Post", "23/9/2020", new User("Waleed")));
        posts.add(new Post(6, "Our 6th Post", "23/9/2020", new User("Nour")));
        posts.add(new Post(7, "Our 7th Post", "23/9/2020", new User("Shourok")));
        posts.add(new Post(8, "Our 7th Post", "23/9/2020", new User("Moniem")));
        postsAdapter = new PostsAdapter(posts);
        rvPosts.setAdapter(postsAdapter);

//        btnLogout = findViewById(R.id.btn_logout);
//        btnLogout.setOnClickListener(this);
//        tvWelcome = findViewById(R.id.tv_welcome);
    }

    private void openLoginActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void clearCache() {
        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().clear().apply();
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btn_logout) {//Clear Shared Prefs
//            //Open LoginActivity
//            //Finish MainActivity
//            clearCache();
//            openLoginActivity();
//            finish();
//        }
    }
}