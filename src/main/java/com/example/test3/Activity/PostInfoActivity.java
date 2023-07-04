package com.example.test3.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.JavaBean.Comment;
import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.Post;
import com.example.test3.JavaBean.User;
import com.example.test3.MainActivity;
import com.example.test3.R;
import com.example.test3.adpater.CommentAdapter;
import com.example.test3.adpater.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class PostInfoActivity extends AppCompatActivity {
    TextView tv_title;
    TextView tv_content;
    TextView tv_author;
    Button btn_edit;
    Button btn_delete;
    Button btn_comment;
    ListView listView;
    Post post = new Post();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        post = (Post) bundle.get("Post");
        initView();
        initData();
        BmobQuery<Comment> bmobQuery = new BmobQuery<Comment>();
        //bmobQuery.addWhereEqualTo("PostId", post.getObjectId());
        //bmobQuery.include("PostId");
        bmobQuery.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> queryList, BmobException e) {
                if (e == null) {
                    List<Comment> list= new ArrayList<>();
                    int n = queryList.size();
                    //Toast.makeText(PostInfoActivity.this, "总评论个数"+n,Toast.LENGTH_SHORT).show();
                    for(int i = 0; i < n ; i++){
                        Comment nowComment = queryList.get(i);
                        if(Objects.equals(nowComment.getPostId(), post.getObjectId())) list.add(nowComment);
                    }
                    listView.setAdapter(new CommentAdapter(PostInfoActivity.this, list));

                }
                else {
                    Toast.makeText(PostInfoActivity.this, "加载失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                if(user.getName().equals(post.getAuthorName())){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Post",post);
                    Intent intent = new Intent(PostInfoActivity.this, PostEditActivity.class);
                    intent.putExtra("userData", bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(PostInfoActivity.this, "仅发布者可以编辑",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                if(user.getName().equals(post.getAuthorName())) {
                    post.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            Toast.makeText(PostInfoActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PostInfoActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    Toast.makeText(PostInfoActivity.this, "仅发布者可以删除",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = BmobUser.getCurrentUser(User.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Post", post);
                Intent intent = new Intent(PostInfoActivity.this, NewCommentActivity.class);
                intent.putExtra("userData", bundle);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Comment comment = (Comment) parent.getItemAtPosition(position);
            if(Objects.equals(comment.getAuthorName(), BmobUser.getCurrentUser(User.class).getName())){
                AlertDialog.Builder builder = new AlertDialog.Builder(PostInfoActivity.this);
                builder.setTitle("删除评论").setMessage("确认删除该评论？").setPositiveButton("确认删除",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                comment.delete(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e == null){
                                            Toast.makeText(PostInfoActivity.this,
                                                    "删除成功",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(PostInfoActivity.this,
                                                    "删除失败",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
            return false;
        });
    }

    private void initData() {
        tv_title.setText("标题：" + post.getTitle());
        tv_author.setText("发布者:" + post.getAuthorName());
        tv_content.setText(String.valueOf(post.getContent()));

    }

    private void initView() {
        btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        listView = (ListView) findViewById(R.id.listView);
    }

}