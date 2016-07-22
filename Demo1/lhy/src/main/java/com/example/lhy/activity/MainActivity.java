package com.example.lhy.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.lhy.R;
import com.example.lhy.adapter.TabAdapter;
import com.example.lhy.application.MainApplication;
import com.example.lhy.util.ActivityUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zxing.activity.CaptureActivity;
import zxing.decode.DecodeThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE = 0;
    @InjectView(R.id.main_mine_btn)
    ImageButton mMainMineBtn;
    @InjectView(R.id.more_action_btn)
    ImageButton mMoreActionBtn;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tab_layout)
    TabLayout mTabLayout;
    @InjectView(R.id.arrow_btn)
    ImageButton mArrowBtn;
    @InjectView(R.id.tab_container_ll)
    LinearLayout mTabContainerLl;
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    private String[] mTitles;
    private String[] mColorPrimary;
    private String[] mColorPrimaryDark;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initViewPager();
    }

    private void initViewPager() {

        mTitles = getResources().getStringArray(R.array.indicator_titles);
        mColorPrimary = getResources().getStringArray(R.array.colorPrimary);
        mColorPrimaryDark = getResources().getStringArray(R.array.colorPrimaryDark);

        mViewpager.setAdapter(new TabAdapter(getSupportFragmentManager(), mTitles.length));
        mTabLayout.setupWithViewPager(mViewpager);

        for (int i = 0; i < mTitles.length; i++) {
            mTabLayout.getTabAt(i).setText(mTitles[i]);
        }

        //设置页面切换的监听, 切换时改变主题颜色
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mToolbar.setBackgroundColor(Color.parseColor(mColorPrimary[position]));
                mTabContainerLl.setBackgroundColor(Color.parseColor(mColorPrimary[position]));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(Color.parseColor(mColorPrimaryDark[position]));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.main_mine_btn, R.id.more_action_btn, R.id.arrow_btn})
    public void onClick(View view) {
        // TODO: 2016/7/18 toolbar和popupwindow中的点击事件
        switch (view.getId()) {
            case R.id.main_mine_btn:
                MainApplication application = (MainApplication) getApplication();
                if (application.mUserInfo != null) {
                    ActivityUtil.startActivity(this, MineActivity.class, false, 0);
                }
                ActivityUtil.startActivity(this, LoginActivity.class, false, 0);
                break;
            case R.id.more_action_btn:
                initPopupWindow();
                break;
            case R.id.arrow_btn:
                break;
            case R.id.foreground_mask:
                mPopupWindow.dismiss();
                break;
            case R.id.scanner_tv:
                mPopupWindow.dismiss();
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.open_local_tv:
                mPopupWindow.dismiss();
                break;
            case R.id.cancel_ib:
                mPopupWindow.dismiss();
                break;
        }
    }

    private void initPopupWindow() {
        View view = View.inflate(this, R.layout.popup_more_action, null);
        mPopupWindow = new PopupWindow(-1, -1);
        mPopupWindow.setContentView(view);
        //设置弹出的窗体为可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //设置popupWindow的显示和消失动画
        mPopupWindow.setAnimationStyle(R.style.popup_anim_style);
        mPopupWindow.update();
        //设置在屏幕底部显示
        mPopupWindow.showAtLocation(this.findViewById(R.id.parent), Gravity.CENTER, 0, 0);
        view.findViewById(R.id.foreground_mask).setOnClickListener(this);
        view.findViewById(R.id.scanner_tv).setOnClickListener(this);
        view.findViewById(R.id.open_local_tv).setOnClickListener(this);
        view.findViewById(R.id.cancel_ib).setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            /*Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();*/
            Bundle extras = data.getExtras();
            if (null != extras) {
                View view = View.inflate(this, R.layout.capture_result_layout, null);
                ImageView mResultImage = (ImageView) view.findViewById(R.id.result_image);
                TextView mResultText = (TextView) view.findViewById(R.id.result_text);
                int width = extras.getInt("width");
                int height = extras.getInt("height");

                LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(width, height);
                lps.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
                lps.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                lps.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

                mResultImage.setLayoutParams(lps);

                String result = extras.getString("result");
                mResultText.setText(result);

                Bitmap barcode = null;
                byte[] compressedBitmap = extras.getByteArray(DecodeThread.BARCODE_BITMAP);
                if (compressedBitmap != null) {
                    barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                    // Mutable copy:
                    barcode = barcode.copy(Bitmap.Config.RGB_565, true);
                }
                mResultImage.setImageBitmap(barcode);

                new AlertDialog.Builder(this).setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        }
    }
}
