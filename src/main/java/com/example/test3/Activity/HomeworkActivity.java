package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.User;
import com.example.test3.R;
import com.example.test3.adpater.HomeworkAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeworkActivity extends AppCompatActivity {
    private ListView listView;
    private Button btn_new;
    private List<Homework> homeworkList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        listView = findViewById(R.id.listview);
        btn_new = findViewById(R.id.btn_new);
        User author = (User) BmobUser.getCurrentUser(User.class);
        /*for(int  i = 1; i < 25; i++){
            homeworkList.add(new Homework("Homework" + i, "description" + i, "subject" + i,
                    "2023.12." + i, "2024.1." + i, author) );
        }*/
        BmobQuery<Homework> bmobQuery = new BmobQuery<Homework>();
        bmobQuery.findObjects(new FindListener<Homework>() {
            @Override
            public void done(List<Homework> list, BmobException e) {
                if (e == null) {

                    listView.setAdapter(new HomeworkAdapter(HomeworkActivity.this, list));

                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Homework homework = (Homework) parent.getItemAtPosition(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Homework",homework);
            Intent intent = new Intent(getApplicationContext(), HomeworkInfoActivity.class);
            intent.putExtra("userData", bundle);
            startActivity(intent);
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                int userStatus = user.getStatus();
                if(userStatus != 2){
                    Toast.makeText(HomeworkActivity.this,"仅教师可以发布作业",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(HomeworkActivity.this, NewHomeworkActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}