package org.lovedev.chapter_4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Kevin
 * @data 2018/5/4
 */
public class CustomView extends View {
    private static final String TAG = "CustomView";
    private final int mWidth = 100;
    private final int mHeight = 100;
    private Paint mPaint;
    private int mColor;

    /**
     * 从代码创建 View 时调用该构造函数
     */
    public CustomView(Context context) {
        this(context, null);
    }

    /**
     * 在 XML 中引用该 View 时调用该构造函数
     */
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 在 XML 中引用该 View，并且有一个 style 样式
     */
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        // mColor 就是通过 app:circle_color 属性设置的颜色属性，如果没有设置就默认是 Color.RED
        mColor = typedArray.getColor(R.styleable.CustomView_circle_color, Color.RED);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        // 一参是圆心的 X 轴坐标，二参是圆心 Y 轴坐标，三参是圆半径，四参是用来画圆的画笔
        canvas.drawCircle(paddingLeft + width / 2,paddingTop + height / 2, radius, mPaint);
    }
}
