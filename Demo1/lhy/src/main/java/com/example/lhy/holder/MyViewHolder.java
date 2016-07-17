package com.example.lhy.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lhy.Protocol.IRecycleViewClickListener;
import com.example.lhy.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 刘焕宇 on 16/7/17.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_image_desc;
    public TextView mTvDesc;
    public TextView tv_author_name;
    public LinearLayout mLlCommentContainer;
    public SimpleDraweeView mDraweeView;
    public SimpleDraweeView sdv_author_head;

    public void setListener(IRecycleViewClickListener listener) {
        mListener = listener;
    }

    private IRecycleViewClickListener mListener;


    public MyViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        mTvDesc = (TextView) view.findViewById(R.id.tv_desc);
        tv_image_desc = (TextView) view.findViewById(R.id.tv_image_desc);
        tv_author_name = (TextView) view.findViewById(R.id.tv_author_name);
        mDraweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        sdv_author_head = (SimpleDraweeView) view.findViewById(R.id.sdv_author_head);
        mLlCommentContainer = (LinearLayout) view.findViewById(R.id.ll_comment_container);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, getPosition());
        }
    }
}