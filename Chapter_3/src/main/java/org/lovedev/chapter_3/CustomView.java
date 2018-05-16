package org.lovedev.chapter_3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author Kevin
 * @data 2018/4/8
 */
public class CustomView extends android.support.v7.widget.AppCompatButton {
    private static final String TAG = "TOUCH CustomView";

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
//        return event.getAction() == MotionEvent.ACTION_DOWN;
        // 如果不消费 ACTION_UP 事件，不会调用 OnClickListener#onClick
        // return !(event.getAction() == MotionEvent.ACTION_UP);
        return super.onTouchEvent(event);
    }
}
