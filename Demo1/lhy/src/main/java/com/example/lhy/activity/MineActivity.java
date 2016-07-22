package com.example.lhy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lhy.Controller.MineController;
import com.example.lhy.IModelChangedListener;
import com.example.lhy.R;
import com.example.lhy.application.MainApplication;
import com.example.lhy.bean.RLoginResult;
import com.example.lhy.fragment.EditInfoFragment;
import com.example.lhy.fragment.FollowFragment;
import com.example.lhy.fragment.LikeFragment;
import com.example.lhy.fragment.MessageFragment;
import com.example.lhy.fragment.MyWorkFragment;
import com.example.lhy.fragment.OfflineWorkFragment;
import com.example.lhy.fragment.SettingFragment;
import com.example.lhy.util.ActivityUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Serena
 * @time 2016/7/17  13:44
 * @desc 登录之后的界面
 */
public class MineActivity extends FragmentActivity implements IModelChangedListener, View.OnClickListener {


    @InjectView(R.id.imageView_mine_background)
    ImageView mImageViewMineBackground;
    @InjectView(R.id.button_back)
    ImageView mButtonBack;
    @InjectView(R.id.iv_edit)
    ImageView mIvEdit;
    @InjectView(R.id.user_background_container)
    FrameLayout mUserBackgroundContainer;
    @InjectView(R.id.imageView_avatar)
    ImageView mImageViewAvatar;
    @InjectView(R.id.textView_username)
    TextView mTextViewUsername;
    @InjectView(R.id.textView_credit)
    TextView mTextViewCredit;
    @InjectView(R.id.textView_username_ll)
    LinearLayout mTextViewUsernameLl;
    @InjectView(R.id.textView_person_desc)
    TextView mTextViewPersonDesc;
    @InjectView(R.id.iv_message)
    ImageView mIvMessage;
    @InjectView(R.id.tv_message)
    TextView mTvMessage;
    @InjectView(R.id.ll_message)
    LinearLayout mLlMessage;
    @InjectView(R.id.iv_follow)
    ImageView mIvFollow;
    @InjectView(R.id.tv_follow)
    TextView mTvFollow;
    @InjectView(R.id.ll_follow)
    LinearLayout mLlFollow;
    @InjectView(R.id.iv_like)
    ImageView mIvLike;
    @InjectView(R.id.tv_like)
    TextView mTvLike;
    @InjectView(R.id.ll_like)
    LinearLayout mLlLike;
    @InjectView(R.id.iv_my_work)
    ImageView mIvMyWork;
    @InjectView(R.id.tv_my_work)
    TextView mTvMyWork;
    @InjectView(R.id.ll_my_work)
    LinearLayout mLlMyWork;
    @InjectView(R.id.iv_offline_work)
    ImageView mIvOfflineWork;
    @InjectView(R.id.tv_offline_work)
    TextView mTvOfflineWork;
    @InjectView(R.id.ll_offline_work)
    LinearLayout mLlOfflineWork;
    @InjectView(R.id.iv_setting)
    ImageView mIvSetting;
    @InjectView(R.id.tv_setting)
    TextView mTvSetting;
    @InjectView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @InjectView(R.id.mine_action_panel)
    LinearLayout mMineActionPanel;
    @InjectView(R.id.placeHold_bottom)
    FrameLayout mPlaceHoldBottom;
    @InjectView(R.id.parent_rl)
    RelativeLayout mParentRl;

    private MineController mMineController;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mine);
        ButterKnife.inject(this);

        initController();
        MainApplication application = (MainApplication) getApplication();
        RLoginResult userInfo = application.mUserInfo;
        mTextViewUsername.setText(userInfo.getUserName());
        mImageViewAvatar.setImageResource(R.mipmap.ic_launcher);
        mTextViewPersonDesc.setText("哈哈哈...");
        //这里是通过ActivityUtil跳转到homeActivity
        mButtonBack.setOnClickListener(this);
        mIvEdit.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
        mLlFollow.setOnClickListener(this);
        mLlLike.setOnClickListener(this);
        mLlMyWork.setOnClickListener(this);
        mLlOfflineWork.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);
    }

    private void initController() {
        mMineController = new MineController(this);
        mMineController.setListener(this);
    }

    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_back:
                //点击回退到home界面 TODO
                ActivityUtil.startActivity(this,MainActivity.class,false,0);
                break;
            case R.id.iv_edit:
                //点击进入用户信息编辑界面
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new EditInfoFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new MessageFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_follow:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new FollowFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_like:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new LikeFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_my_work:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new MyWorkFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_offline_work:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new OfflineWorkFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
            case R.id.ll_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.parent_rl, new SettingFragment()).commit();
                mButtonBack.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);
                break;
        }
    }
}
