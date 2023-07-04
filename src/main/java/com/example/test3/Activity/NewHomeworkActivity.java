package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.User;
import com.example.test3.R;

import javax.microedition.khronos.egl.EGL;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewHomeworkActivity extends AppCompatActivity {
    EditText et_title;
    EditText et_describe;
    EditText et_subject;
    EditText et_beginTime;
    EditText et_ddl;
    Button btn_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_homework);
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
                String subject = et_subject.getText().toString();
                String beginTime = et_beginTime.getText().toString();
                String ddl = et_ddl.getText().toString();
                Homework homework = new Homework();
                homework.setTitle(title);
                homework.setDescribe(describe);
                homework.setSubject(subject);
                homework.setBeginTime(beginTime);
                homework.setDdl(ddl);
                homework.setAuthor(user);
                homework.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewHomeworkActivity.this, HomeworkActivity.class);
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
        et_subject = (EditText) findViewById(R.id.et_subject);
        et_beginTime = (EditText) findViewById(R.id.et_beginTime);
        et_ddl = (EditText) findViewById(R.id.et_ddl);
        btn_new = (Button) findViewById(R.id.btn_new);
    }
}