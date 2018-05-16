package org.lovedev.chapter_3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Kevin
 * @data 2018/4/8
 */
public class CustomViewGroup extends LinearLayout {
    private static final String TAG = "TOUCH CustomViewGroup";

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: " + ev.getAction());
        // 此处根据业务以及场景判断是否需要拦截事件
        boolean needIntercept = true;
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 因为如果 ACTION_DOWN 事件拦截之后，同一事件序列中的其他事件只会交给该 ViewGroup 处理，这里只能返回 false
                intercept = false;
                break;
            case MotionEvent.ACTION_UP:
                // 这里必须要返回 false，因为ViewGroup 只要拦截了任何事件，剩下的事件也只会交给他处理
                // 如果在没有拦截 ACTION_MOVE 的情况下，返回了 true，子 View 的 click 事件将不会执行
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                intercept = needIntercept;
                break;
                default:
                break;
        }
        return intercept;
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " +  event.getAction());
        return true;
    }
}
