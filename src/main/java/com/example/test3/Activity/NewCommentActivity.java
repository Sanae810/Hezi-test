package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test3.JavaBean.Comment;
import com.example.test3.JavaBean.Post;
import com.example.test3.JavaBean.User;
import com.example.test3.MainActivity;
import com.example.test3.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewCommentActivity extends AppCompatActivity {
    Post post;
    EditText et_content;
    Button btn_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        post = (Post) bundle.get("Post");
        initView();
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                Comment comment = new Comment();
                comment.setAuthor(user);
                comment.setAuthorName(user.getName());
                comment.setContent(et_content.getText().toString());
                comment.setPost(post);
                comment.setPostId(post.getObjectId());
                comment.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        Toast.makeText(NewCommentActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewCommentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }


    private void initView() {
        et_content = (EditText) findViewById(R.id.et_describe);
        btn_new = (Button) findViewById(R.id.btn_new);
    }
}