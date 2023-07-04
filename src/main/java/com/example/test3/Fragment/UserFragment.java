package com.example.test3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test3.Activity.HomeworkActivity;
import com.example.test3.Activity.SettingActivity;
import com.example.test3.JavaBean.User;
import com.example.test3.R;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;

public class UserFragment extends BaseFragment {

    private ImageView img;
    private TextView tx_name;
    private TextView tx_status;
    private TextView tx_ID;
    private Button btn_moreInfo;
    private Button btn_setting;
    private Button btn_contact;
    private Button btn_adrress;
    private Button btn_checkVersion;
    private Button btn_feedback;
    private Button btn_plus;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView();
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (BmobUser.isLogin()) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Snackbar.make(view, "当前用户：" + user.getUsername() , Snackbar.LENGTH_LONG).show();
                    String username = (String) BmobUser.getObjectByKey("username");
                    String objId = (String) BmobUser.getObjectByKey("objectId");
                    Snackbar.make(view, "当前用户属性：" + objId, Snackbar.LENGTH_LONG).show();
                //}
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_user,null);
        img = (ImageView) view.findViewById(R.id.img);
        tx_name = (TextView) view.findViewById(R.id.tx_name);
        tx_status = (TextView) view.findViewById(R.id.tx_status);
        tx_ID= (TextView) view.findViewById(R.id.tx_ID);
        btn_moreInfo= (Button) view.findViewById(R.id.btn_moreInfo);
        btn_setting = (Button) view.findViewById(R.id.btn_setting);
        btn_adrress = (Button) view.findViewById(R.id.btn_address);
        btn_checkVersion = (Button) view.findViewById(R.id.btn_checkVersion);
        btn_contact = (Button) view.findViewById(R.id.btn_contactUs);
        btn_feedback = (Button)view.findViewById(R.id.btn_feedback);
        btn_plus = (Button) view.findViewById(R.id.btn_plus);
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            String username = user.getName();
            String Id = user.getUsername();
            tx_name.setText("姓名 ： " + username);
            tx_ID.setText("ID ： " + Id);
            int status = user.getStatus();
            switch (status){
                case 1:
                    tx_status.setText("身份 ： 学生");
                    break;
                case 2:
                    tx_status.setText("身份 ： 教师");
                    break;
                case 3:
                    tx_status.setText("身份 ： 管理员");
                    break;
            }
        }

        return view;
    }
    @Override
    public void initData() {
        super.initData();
    }

}
