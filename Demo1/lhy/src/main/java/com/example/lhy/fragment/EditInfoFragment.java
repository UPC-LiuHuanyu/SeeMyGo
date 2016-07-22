package com.example.lhy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lhy.R;
import com.example.lhy.application.MainApplication;
import com.example.lhy.bean.RLoginResult;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Serena
 * @time 2016/7/18  23:10
 * @desc 个人信息编辑页面
 */
public class EditInfoFragment extends BaseFragment {


    @InjectView(R.id.button_back)
    ImageView mButtonBack;
    @InjectView(R.id.title_headView)
    LinearLayout mTitleHeadView;
    @InjectView(R.id.imageView_avatar)
    ImageView mImageViewAvatar;
    @InjectView(R.id.headPic_ll)
    LinearLayout mHeadPicLl;
    @InjectView(R.id.name_edit)
    EditText mNameEdit;
    @InjectView(R.id.name_ll)
    LinearLayout mNameLl;
    @InjectView(R.id.content_edit)
    EditText mContentEdit;
    @InjectView(R.id.desc_ll)
    LinearLayout mDescLl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editinfo, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainApplication application = (MainApplication) getActivity().getApplication();
        RLoginResult userInfo = application.mUserInfo;
        mNameEdit.setText(userInfo.getUserName());
        mImageViewAvatar.setImageResource(R.mipmap.ic_launcher);
        mContentEdit.setText("哈哈哈...");
        mButtonBack.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
