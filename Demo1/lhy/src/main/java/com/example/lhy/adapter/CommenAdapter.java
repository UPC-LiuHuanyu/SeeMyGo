package com.example.lhy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lhy.Protocol.IRecycleViewClickListener;
import com.example.lhy.R;
import com.example.lhy.bean.RItembean;
import com.example.lhy.holder.MyViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/15.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class CommenAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_FOOTER = 2;
    List<RItembean> mDatas;
    IRecycleViewClickListener listener;
    private Context context;
    private View mHeaderView;
    private View mFooterView;
    private int mHeaderCount = 0;


    public CommenAdapter(Context context) {
        this.context = context;
        mDatas = new ArrayList<>();
    }

    public void setDatas(List<RItembean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
        mFooterView.setVisibility(View.VISIBLE);
    }

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        mHeaderCount++;
        notifyDataSetChanged();
    }

    public void setItemOnClickListener(IRecycleViewClickListener listener) {
        this.listener = listener;
    }


    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return ITEM_TYPE_HEADER;
        } else if (position < getItemCount() - 1) {
            return ITEM_TYPE_CONTENT;
        } else {
            return ITEM_TYPE_FOOTER;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == ITEM_TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        } else if (viewType == ITEM_TYPE_CONTENT) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_commen, parent, false);
            MyViewHolder holder = new MyViewHolder(inflate);
            holder.setListener(listener);
            return holder;
        } else {
            mFooterView = LayoutInflater.from(context)
                    .inflate(R.layout.item_footer, parent, false);
            mFooterView.setVisibility(View.GONE);
            return new MyViewHolder(mFooterView);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_HEADER
                || getItemViewType(position) == ITEM_TYPE_FOOTER) {
            return;
        }

        int realPosition = holder.getLayoutPosition() - mHeaderCount;

        RItembean itembean = mDatas.get(realPosition);
        holder.mDraweeView.setImageURI(itembean.thumburl);
        holder.sdv_author_head.setImageURI(itembean.author.headphoto);

        String nickname = itembean.author.nickname;
        if (!TextUtils.isEmpty(nickname)) {
            holder.tv_author_name.setText(nickname);
        }
        String name = itembean.name;
        if (!TextUtils.isEmpty(name)) {
            holder.tv_image_desc.setText(name);
        }
        String imagedes = itembean.imagedes;
        if (!TextUtils.isEmpty(imagedes)) {
            holder.mTvDesc.setText(imagedes);
        }

        List<RItembean.CommentsBean> comments = itembean.comments;

        holder.mLlCommentContainer.removeAllViews();
        for (int i = 0; i < 3 && i < comments.size(); i++) {
            View inflate = View.inflate(context, R.layout.item_comment, null);
            SimpleDraweeView mSvHead = (SimpleDraweeView) inflate.findViewById(R.id.iv_head);
            TextView mTvUserName = (TextView) inflate.findViewById(R.id.tv_user_name);
            TextView mTvComment = (TextView) inflate.findViewById(R.id.tv_comment);
            RItembean.CommentsBean bean = comments.get(i);
            Uri uri = Uri.parse(bean.author.headphoto);
            mSvHead.setImageURI(uri);
            mTvUserName.setText(bean.author.nickname);
            mTvComment.setText(bean.content);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, 0, 1);
            inflate.setLayoutParams(params);
            holder.mLlCommentContainer.addView(inflate);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? mHeaderCount + 1 : mDatas.size() + mHeaderCount + 1;
    }

}
