package com.example.test3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.test3.Activity.HomeworkActivity;
import com.example.test3.Activity.HomeworkInfoActivity;
import com.example.test3.Activity.NewHomeworkActivity;
import com.example.test3.Activity.NewPostActivity;
import com.example.test3.Activity.PostInfoActivity;
import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.Post;
import com.example.test3.JavaBean.User;
import com.example.test3.MainActivity;
import com.example.test3.R;
import com.example.test3.adpater.HomeworkAdapter;
import com.example.test3.adpater.PostAdapter;

import java.util.List;
import java.util.Objects;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NewsFragment extends BaseFragment{

    private TextView tv;
    private ListView listView;
    private Button btn_new;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        listView = (ListView) view.findViewById(R.id.listView);
        btn_new = (Button) view.findViewById(R.id.btn_new);
        BmobQuery<Post> bmobQuery = new BmobQuery<Post>();
        bmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {

                    listView.setAdapter(new PostAdapter(getActivity(), list));

                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });

        listView.setOnItemClickListener((parent, viewList, position, id) -> {
            Post post = (Post) parent.getItemAtPosition(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Post",post);
            Intent intent = new Intent(getActivity(), PostInfoActivity.class);
            intent.putExtra("userData", bundle);
            startActivity(intent);
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                if(user.getStatus()==3){
                    Intent intent = new Intent(getActivity(), NewPostActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "仅管理员可发布通知", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public View initView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
