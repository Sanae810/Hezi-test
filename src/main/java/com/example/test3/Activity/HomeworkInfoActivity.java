package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.User;
import com.example.test3.R;

import cn.bmob.v3.BmobUser;

public class HomeworkInfoActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mDescribe;
    TextView mBeginTime;
    TextView mDdl;
    TextView mSubject;
    TextView mAuthor;

    Button btn_download;
    Button btn_submit;
    Button btn_update;

    Homework homework;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        homework = (Homework) bundle.get("Homework");
        initView();
        initData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                if(user.getName().equals(homework.getAuthorName())){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Homework",homework);
                    Intent intent = new Intent(HomeworkInfoActivity.this, HomeworkEditActivity.class);
                    intent.putExtra("userData", bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(HomeworkInfoActivity.this, "仅发布者可以编辑",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initData() {
        mTitle.setText("标题：" + String.valueOf(homework.getTitle()));
        mDescribe.setText(String.valueOf(homework.getDescribe()));
        mSubject.setText("课程：" + String.valueOf(homework.getSubject()));
        mBeginTime.setText("开始日期：" + String.valueOf(homework.getBeginTime()));
        mDdl.setText("截止日期：" + String.valueOf(homework.getDdl()));
        mAuthor.setText("教师：" + String.valueOf(homework.getAuthorName()));
    }

    private void initView() {
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_update = (Button)  findViewById(R.id.btn_update);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mDescribe = (TextView) findViewById(R.id.tv_describe);
        mBeginTime = (TextView) findViewById(R.id.tv_beginTime);
        mDdl = (TextView) findViewById(R.id.tv_ddl);
        mSubject = (TextView) findViewById(R.id.tv_subject);
        mAuthor = (TextView) findViewById(R.id.tv_author);
    }

}