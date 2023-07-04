package com.example.test3.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.JavaBean.Homework;
import com.example.test3.JavaBean.User;
import com.example.test3.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeworkAdapter extends BaseAdapter {
    Context context;
    List<Homework> homeworkList;

    public HomeworkAdapter(Context context, List<Homework> homeworkList) {
        this.context = context;
        this.homeworkList = homeworkList;
    }
    @Override
    public int getCount() {
        return homeworkList == null? 0 : homeworkList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeworkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context, R.layout.adapter_homework_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Homework homework = homeworkList.get(i);
        /*User author = homework.getAuthor();
        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.addWhereEqualTo("objectId", authorId.getObjectId());
        bmobQuery.findObjects(new FindListener<User>() {
            @SuppressLint("WrongConstant")
            @Override
            public void done(List<User> list, BmobException e) {
                if(e==null) {
                    int n = list.size();
                    for(int i = 0; i < n ; i++){
                        User author = list.get(i);
                        viewHolder.mTitle.setText("标题：" + String.valueOf(homework.getTitle()));
                        viewHolder.mDescribe.setText(String.valueOf(homework.getDescribe()));
                        viewHolder.mSubject.setText("课程：" + String.valueOf(homework.getSubject()));
                        viewHolder.mBeginTime.setText("开始日期：" + String.valueOf(homework.getBeginTime()));
                        viewHolder.mDdl.setText("截止日期：" + String.valueOf(homework.getDdl()));
                        viewHolder.mAuthor.setText("教师：" + author.getName());
                    }

                }
            }
        });*/
        viewHolder.mTitle.setText("标题：" + String.valueOf(homework.getTitle()));
        viewHolder.mDescribe.setText(String.valueOf(homework.getDescribe()));
        viewHolder.mSubject.setText("课程：" + String.valueOf(homework.getSubject()));
        viewHolder.mBeginTime.setText("开始日期：" + String.valueOf(homework.getBeginTime()));
        viewHolder.mDdl.setText("截止日期：" + String.valueOf(homework.getDdl()));
        viewHolder.mAuthor.setText("教师：" + String.valueOf(homework.getAuthorName()));
        return view;
    }
    static class ViewHolder{
        View view;
        TextView mTitle;
        TextView mDescribe;
        TextView mBeginTime;
        TextView mDdl;
        TextView mSubject;
        TextView mAuthor;
        public ViewHolder(View view) {
            this.view = view;
            this.mTitle = (TextView) view.findViewById(R.id.tv_title);
            this.mDescribe = (TextView) view.findViewById(R.id.tv_describe);
            this.mBeginTime = (TextView) view.findViewById(R.id.tv_beginTime);
            this.mDdl = (TextView) view.findViewById(R.id.tv_ddl);
            this.mSubject = (TextView) view.findViewById(R.id.tv_subject);
            this.mAuthor = (TextView) view.findViewById(R.id.tv_author);
        }
    }
}
