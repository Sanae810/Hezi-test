package com.example.test3.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.test3.JavaBean.Post;
import com.example.test3.R;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    Context context;
    List<Post> postList;

    public PostAdapter(FragmentActivity activity, List<Post> postList) {
        this.postList = postList;
        this.context = activity;
    }

    @Override
    public int getCount() {
        return postList == null? 0 : postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context, R.layout.adapter_post_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Post post = postList.get(i);
        viewHolder.mTitle.setText("标题：" + post.getTitle());
        viewHolder.mContent.setText(String.valueOf(post.getContent()));
        viewHolder.mAuthor.setText("作者:" + post.getAuthorName());
        //viewHolder.mLikes.setText("赞同：" + post.getLikes());
        return view;
    }
    static class ViewHolder{
        View view;
        TextView mTitle;
        TextView mContent;
        TextView mAuthor;
        TextView mLikes;
        public ViewHolder(View view) {
            this.view = view;
            this.mTitle = (TextView) view.findViewById(R.id.tv_title);
            this.mContent = (TextView) view.findViewById(R.id.tv_content);
            this.mAuthor = (TextView) view.findViewById(R.id.tv_author);
            this.mLikes = (TextView) view.findViewById(R.id.tv_likes);
        }
    }
}
