package com.example.lhy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lhy.Controller.LoginController;
import com.example.lhy.IModelChangedListener;
import com.example.lhy.R;
import com.example.lhy.activity.MineActivity;
import com.example.lhy.bean.RResult;
import com.example.lhy.bean.UserInfo;
import com.example.lhy.cons.IDiyMessage;
import com.example.lhy.util.ActivityUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Serena
 * @time 2016/7/16  20:56
 * @desc ${TODD}
 */
public class LoginFragment extends Fragment implements View.OnClickListener, IModelChangedListener {


    @InjectView(R.id.imageView_greetings)
    ImageView mImageViewGreetings;
    @InjectView(R.id.user_login_name_edit)
    EditText mUserLoginNameEdit;
    @InjectView(R.id.password_edit)
    EditText mPasswordEdit;
    @InjectView(R.id.textView_forget_password)
    TextView mTextViewForgetPassword;
    @InjectView(R.id.login_button)
    Button mLoginButton;
    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.login_weixin_button)
    ImageView mLoginWeixinButton;
    @InjectView(R.id.login_qq_button)
    ImageView mLoginQqButton;
    @InjectView(R.id.login_weibo_icon)
    ImageView mLoginWeiboIcon;
    @InjectView(R.id.third_login_container)
    LinearLayout mThirdLoginContainer;
    @InjectView(R.id.switch_to_register_button)
    TextView mSwitchToRegisterButton;

    private LoginController mLoginController;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDiyMessage.LOGIN_ACTION_RESULT:
                    hanleLoginResult((RResult) msg.obj);
                    break;
                case IDiyMessage.SAVE_USER_ACTION_RESULT:
                    handleSaveUserInfoResult((Boolean) msg.obj);
                    break;
                case IDiyMessage.QUERY_USER_ACTION_RESULT:
                    handleQueryUserInfo((UserInfo)msg.obj);
                    break;
            }
        }
    };

    private void handleQueryUserInfo(UserInfo obj) {
        if(obj!=null) {
            mUserLoginNameEdit.setText(obj.getUsername());
        }
    }

    private void handleSaveUserInfoResult(Boolean obj) {
        if (obj) {
            ActivityUtil.startActivity(getActivity(), MineActivity.class, true, 0);
        } else {
            Toast.makeText(getActivity(), "保存用户信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void hanleLoginResult(RResult obj) {
        if (!obj.isSuccess()) {
            Toast.makeText(getActivity(), "登录失败" + obj.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        String name = mUserLoginNameEdit.getText().toString().trim();
        String pwd = mPasswordEdit.getText().toString().trim();
        String result = obj.getResult();
        mLoginController.sendAsyncMessage(IDiyMessage.SAVE_USER_ACTION,
                result, name, pwd);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoginButton.setOnClickListener(this);

        mLoginController = new LoginController(getActivity());
        mLoginController.setListener(this);
        mLoginController.sendAsyncMessage(IDiyMessage.QUERY_USER_ACTION,0);
/*
        if (mUserLoginNameEdit.isFocused()) {
            mUserLoginNameEdit.setHintTextColor(getResources().getColor(R.color.colorBase));
        }*/

        String text = "请输入您的 手机号 或 邮箱";
        String text1 = "  请输入您的   账号密码";
        mUserLoginNameEdit.setHint(text);
        mPasswordEdit.setHint(text1);
        mUserLoginNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    int length = s.toString().length();
                    if (length == 3 || length == 8) {
                        mUserLoginNameEdit.setText(s + " ");
                        mUserLoginNameEdit.setSelection(mUserLoginNameEdit.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isPhone(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public void onClick(View v) {
        String name = mUserLoginNameEdit.getText().toString();
        String pwd = mPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(getContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }
        if (!isPhone(mUserLoginNameEdit.getText().toString())
                || mUserLoginNameEdit.getInputType() != InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
            Toast.makeText(getContext(), "账号或密码格式不对", Toast.LENGTH_SHORT).show();
        }
        mLoginController.sendAsyncMessage(IDiyMessage.LOGIN_ACTION, name, pwd);
    }

    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
