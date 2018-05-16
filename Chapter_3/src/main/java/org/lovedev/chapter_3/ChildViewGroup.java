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
public class ChildViewGroup extends LinearLayout {
    private static final String TAG = "TOUCH ChildViewGroup";

    public ChildViewGroup(Context context) {
        super(context);
    }

    public ChildViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChildViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent");
        // 此处根据业务以及场景判断外部元素是否要拦截事件
        boolean parentNeedIntercept = true;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 不允许父元素拦截 ACTION_DOWN
                this.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                // ACTION_UP 事件不用处理，系统会自动交给拦截 ACTION_MOVE 事件的元素处理
                break;
            case MotionEvent.ACTION_MOVE:
                // 不允许父元素拦截 ACTION_MOVE
                this.requestDisallowInterceptTouchEvent(parentNeedIntercept);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        return true;
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        return true;
//        return event.getAction() == MotionEvent.ACTION_DOWN | event.getAction() == MotionEvent.ACTION_UP;
    }
}
