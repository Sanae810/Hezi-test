package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test3.JavaBean.User;
import com.example.test3.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText rgsID, rgsName, rgsPsw1, rgsPsw2;
    //private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//禁止横屏
        setContentView(R.layout.activity_register);
        setTitle("用户注册");//顶部标题改成用户注册

        initView();//初始化界面
        //mDBOpenHelper = new DBOpenHelper(this);
    }

    private void initView() {
        rgsID = findViewById(R.id.rgsID);
        rgsName = findViewById(R.id.rgsName);
        rgsPsw1 = findViewById(R.id.rgsPsw1);
        rgsPsw2 = findViewById(R.id.rgsPsw2);

        Button btn_register = findViewById(R.id.btn_rgs);
        ImageView iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://返回登录界面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_rgs://注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = rgsName.getText().toString().trim();
                String userID = rgsID.getText().toString().trim();
                String password1 = rgsPsw1.getText().toString().trim();
                String password2 = rgsPsw2.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(userID) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
                    //判断两次密码是否一致
                    if (password1.equals(password2)) {
                        //将用户名和密码加入到数据库中
                        final User user = new User();
                        user.setUsername(userID);
                        user.setName(username);
                        user.setPassword(password1);
                        user.signUp(new SaveListener<User>() {
                            @Override
                            public void done(User user, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "注册失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                        });

                    } else {
                        Toast.makeText(this, "两次密码不一致,注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "注册信息不完善,注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
