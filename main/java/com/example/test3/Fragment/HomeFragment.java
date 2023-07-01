package com.example.test3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test3.Activity.HomeworkActivity;
import com.example.test3.JavaBean.User;
import com.example.test3.R;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;

public class HomeFragment  extends BaseFragment{
    private Button button1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        button1 = (Button) view.findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.isLogin()) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Snackbar.make(view, "当前用户：" + user.getUsername() , Snackbar.LENGTH_LONG).show();
                    String username = (String) BmobUser.getObjectByKey("username");
                    String objId = (String) BmobUser.getObjectByKey("objectId");
                    Snackbar.make(view, "当前用户属性：" + objId, Snackbar.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getActivity(), HomeworkActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public View initView() {
            View view = View.inflate(getActivity(), R.layout.fragment_home,null);
            button1 = (Button) view.findViewById(R.id.btn1);
            return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
