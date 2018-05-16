package org.lovedev.chapter_4;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author Kevin
 * @data 2018/5/4
 */
public class CustomView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "CustomView";
    private final int mWidth = 100;
    private final int mHeight = 100;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.AT_MOST == widthMode && MeasureSpec.AT_MOST == heightMode) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (MeasureSpec.AT_MOST == widthMode) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (MeasureSpec.AT_MOST == heightMode) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }
}
