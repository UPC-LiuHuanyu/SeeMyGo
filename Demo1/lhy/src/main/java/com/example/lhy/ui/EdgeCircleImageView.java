package com.example.lhy.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 刘焕宇 on 16/7/15.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class EdgeCircleImageView extends SimpleDraweeView {

    private boolean mRoundAsCircle;
    private float mCorner;

    public EdgeCircleImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);

    }

    public EdgeCircleImageView(Context context) {
        super(context);
    }

    public EdgeCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, com.facebook.drawee.backends.pipeline.R.styleable.GenericDraweeHierarchy);
        mRoundAsCircle = typedArray.getBoolean(com.facebook.drawee.backends.pipeline.R.styleable.GenericDraweeHierarchy_roundAsCircle, false);
        mCorner = typedArray.getDimension(com.facebook.drawee.backends.pipeline.R.styleable.GenericDraweeHierarchy_roundedCornerRadius, 0);
    }

    public EdgeCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EdgeCircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        RectF rectF = new RectF(2, 2, width - 2, height - 2);
        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 230, 230, 230));
        paint.setStyle(Paint.Style.STROKE);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        if (mRoundAsCircle) {
            paint.setStrokeWidth(4);
            canvas.drawArc(rectF, 0, 360, false, paint);
        } else if (mCorner != 0) {
            paint.setStrokeWidth(8);
            canvas.drawRoundRect(rectF, mCorner, mCorner, paint);
        }

    }
}
