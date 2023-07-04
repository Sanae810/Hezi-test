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
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class PostEditActivity extends AppCompatActivity {
    EditText et_title;
    EditText et_describe;

    Button btn_finish;

    Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        post = (Post) bundle.get("Post");
        initView();
        initData();

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                String title = et_title.getText().toString();
                String describe = et_describe.getText().toString();
                String authorName = post.getAuthorName();
                User author = post.getAuthor();
                BmobRelation likes = post.getLikes();
                post.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });
                post.setTitle(title);
                post.setContent(describe);
                post.setAuthorName(authorName);
                post.setAuthor(author);
                post.setLikes(likes);
                post.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PostEditActivity.this, MainActivity.class);
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

    private void initData() {
        et_title.setHint("标题：" + post.getTitle());
        et_describe.setHint(String.valueOf(post.getContent()));

    }

    private void initView() {
        btn_finish = (Button) findViewById(R.id.btn_finish);
        et_title = (EditText) findViewById(R.id.et_title);
        et_describe = (EditText) findViewById(R.id.et_describe);
    }

}