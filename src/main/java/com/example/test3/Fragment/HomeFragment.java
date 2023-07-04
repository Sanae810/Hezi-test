package com.example.test3.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test3.Activity.AffairsActivity;
import com.example.test3.Activity.GroupsActivity;
import com.example.test3.Activity.HomeworkActivity;
import com.example.test3.Activity.ScheduleActivity;
import com.example.test3.Activity.SettingActivity;
import com.example.test3.JavaBean.User;
import com.example.test3.R;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;

public class HomeFragment  extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn_homework = (Button) view.findViewById(R.id.btn_homework);
        Button btn_schedule = (Button) view.findViewById(R.id.btn_schedule);
        Button btn_affairs = (Button) view.findViewById(R.id.btn_affairs);
        Button btn_groups = (Button) view.findViewById(R.id.btn_groups);
        Button btn_more = (Button) view.findViewById(R.id.btn_more);
        TextView tv_welcome = (TextView) view.findViewById(R.id.tv_welcome);
        User user = BmobUser.getCurrentUser(User.class);
        tv_welcome.setText("Hi!" + user.getName().toString());
        btn_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (BmobUser.isLogin()) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Snackbar.make(view, "当前用户：" + user.getUsername() , Snackbar.LENGTH_LONG).show();
                    String username = (String) BmobUser.getObjectByKey("username");
                    String objId = (String) BmobUser.getObjectByKey("objectId");
                    Snackbar.make(view, "当前用户属性：" + objId, Snackbar.LENGTH_LONG).show();
                }*/
                Intent intent = new Intent(getActivity(), HomeworkActivity.class);
                startActivity(intent);
            }
        });
        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intent);
            }
        });
        btn_affairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AffairsActivity.class);
                startActivity(intent);
            }
        });
        btn_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), GroupsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public View initView() {
            View view = View.inflate(getActivity(), R.layout.fragment_home,null);
            return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
