package com.example.lhy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lhy.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Serena
 * @time 2016/7/19  21:54
 * @desc ${TODD}
 */
public class MyWorkFragment extends BaseFragment {


    @InjectView(R.id.button_back)
    ImageView mButtonBack;
    @InjectView(R.id.title_headView)
    LinearLayout mTitleHeadView;
    @InjectView(R.id.message_rl)
    RelativeLayout mMessageRl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mywork, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButtonBack.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
