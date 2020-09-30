package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ADD_POST_CODE = 2;
    private TextView tvWelcome;
    private String email, name;
    private Button btnLogout;
    private RecyclerView rvPosts;
    private List<Post> posts = new ArrayList<>();
    private PostsAdapter postsAdapter;
    private FloatingActionButton fabAddNewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getUserDetailsFromCache();
        populateViews();
    }

    private void populateViews() {
        tvWelcome.setText(String.format(Locale.getDefault(), "%s %s", "Welcome", name));
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
        tvWelcome = findViewById(R.id.tv_welcome);
        fabAddNewPost = findViewById(R.id.fb_add_post);
        fabAddNewPost.setOnClickListener(this);
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

    }

    private void openLoginActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void clearCache() {
        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().clear().apply();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fb_add_post) {
            startActivityForResult(new Intent(MainActivity.this, AddPostActivity.class), ADD_POST_CODE);
        }
    }


    //Inflation of menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Click listener of menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                //Clear Shared Prefs
                // Open LoginActivity
                //Finish MainActivity
                clearCache();
                openLoginActivity();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && requestCode == ADD_POST_CODE) {
            posts.add((Post) intent.getSerializableExtra("post"));
            postsAdapter.setPosts(posts);
        }
    }
}


