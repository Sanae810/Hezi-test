package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.User;
import com.example.test3.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class HomeworkEditActivity extends AppCompatActivity {
    EditText et_title;
    EditText et_describe;
    EditText  et_beginTime;
    EditText et_ddl;
    EditText  et_subject;
    EditText  et_author;

    Button btn_finish;

    Homework homework;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_edit);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        homework = (Homework) bundle.get("Homework");
        initView();
        initData();

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                String title = et_title.getText().toString();
                String describe = et_describe.getText().toString();
                String subject = et_subject.getText().toString();
                String beginTime = et_beginTime.getText().toString();
                String ddl = et_ddl.getText().toString();
                homework.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });
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
                            Intent intent = new Intent(HomeworkEditActivity.this, HomeworkActivity.class);
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
        et_title.setHint("标题：" + homework.getTitle());
        et_describe.setHint(String.valueOf(homework.getDescribe()));
        et_subject.setHint("课程：" + homework.getSubject());
        et_beginTime.setHint("开始日期：" + homework.getBeginTime());
        et_ddl.setHint("截止日期：" + homework.getDdl());
        et_author.setHint("教师：" + homework.getAuthorName());
    }

    private void initView() {
        btn_finish = (Button) findViewById(R.id.btn_finish);
        et_title = (EditText ) findViewById(R.id.et_title);
        et_describe = (EditText ) findViewById(R.id.et_describe);
        et_beginTime = (EditText ) findViewById(R.id.et_beginTime);
        et_ddl = (EditText ) findViewById(R.id.et_ddl);
        et_subject = (EditText ) findViewById(R.id.et_subject);
        et_author = (EditText ) findViewById(R.id.et_author);
    }

}