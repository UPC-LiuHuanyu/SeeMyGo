package com.example.lhy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lhy.R;
import com.example.lhy.fragment.LoginFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends FragmentActivity {



    @InjectView(R.id.imageView_placehold)
    ImageView mImageViewPlacehold;//左侧占位图
    @InjectView(R.id.qumeng_group_imageview)
    ImageView mQumengGroupImageview;//团队logo
    @InjectView(R.id.fragment_container)
    FrameLayout mFragmentContainer;//包裹loginFragment
    @InjectView(R.id.register_info_container_view)
    LinearLayout mRegisterInfoContainerView;//存放root的容器
    @InjectView(R.id.imageView_close)
    ImageView mImageViewClose;//退出按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_root);
        ButterKnife.inject(this);

        initUI();

    }

    private void initUI() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
    }
}
