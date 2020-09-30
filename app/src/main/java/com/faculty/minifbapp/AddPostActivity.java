package com.faculty.minifbapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etPostBody;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        initViews();
    }

    private void initViews() {
        etPostBody = findViewById(R.id.et_post_body);
        btnPost = findViewById(R.id.btn_post);
        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_post)
            startPostingProcess();
    }

    private void startPostingProcess() {
        String s = null;
        String f = "";
        if (TextUtils.isEmpty(etPostBody.getText()))
            etPostBody.setError(getString(R.string.empty_post_body));
        else {
            //Add the post
            Post post = new Post(9, etPostBody.getText().toString(), "30/9/2020", new User("Ebrahim"));
            Intent intent = new Intent();
            intent.putExtra("post", post);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}