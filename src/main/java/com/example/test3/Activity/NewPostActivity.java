package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.Post;
import com.example.test3.JavaBean.User;
import com.example.test3.MainActivity;
import com.example.test3.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewPostActivity extends AppCompatActivity {
    Button btn_new;
    EditText et_title;
    EditText et_describe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                String title = et_title.getText().toString();
                String describe = et_describe.getText().toString();
                Post post = new Post();
                post.setTitle(title);
                post.setContent(describe);
                post.setAuthorName(user.getName());
                post.setAuthor(user);
                post.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else{
                            //toast("创建数据失败：" + e.getMessage());
                        }
                    }
                });

                //homework.setAuthor(user);
            }
        });
    }

    private void initView() {
        et_title = (EditText) findViewById(R.id.et_title);
        et_describe = (EditText) findViewById(R.id.et_describe);
        btn_new = (Button) findViewById(R.id.btn_new);
    }
}