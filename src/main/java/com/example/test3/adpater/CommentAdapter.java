package com.example.test3.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.JavaBean.Comment;
import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.Post;
import com.example.test3.JavaBean.User;
import com.example.test3.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CommentAdapter extends BaseAdapter {
    Post post;
    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }
    @Override
    public int getCount() {
        return commentList == null? 0 : commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context, R.layout.adapter_comment_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Comment comment = commentList.get(i);

        viewHolder.mContent.setText(String.valueOf(comment.getContent()));
        viewHolder.mAuthor.setText(comment.getAuthorName() + ":");
        //viewHolder.mLikes.setText("开始日期：" + comment.getLikes());
        return view;
    }
    static class ViewHolder{
        View view;
        TextView mContent;
        TextView mAuthor;
        TextView mLikes;
        public ViewHolder(View view) {
            this.view = view;
            this.mContent = (TextView) view.findViewById(R.id.tv_content);
            this.mAuthor = (TextView) view.findViewById(R.id.tv_author);
            this.mLikes = (TextView) view.findViewById(R.id.tv_likes);
        }
    }
}
