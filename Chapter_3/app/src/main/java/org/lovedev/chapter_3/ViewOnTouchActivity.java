package org.lovedev.chapter_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ViewOnTouchActivity extends AppCompatActivity {
    private final static String TAG = "ViewOnTouchActivity";
    private boolean enableBtn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_on_touch);
        final View returnTrue = findViewById(R.id.view_true);
        returnTrue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: returnTrue");
                // 返回 true 代表消费该事件，后续事件都会由 onTouch() 接收处理，不会调用 View 的 onTouchEvent
                return true;
            }
        });

        returnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: returnTrue");
            }
        });

        final View returnFalse = findViewById(R.id.view_false);
        returnFalse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: returnFalse");
                // 返回 false 代表不消费该事件，调用 View 的 onTouchEvent

                // 如果 OnTouchListener#onTouch 只消费 ACTION_DOWN，那么其他事件依然会正常传递到 View#onTouchEvent 处理
                // return event.getAction() == MotionEvent.ACTION_DOWN;

                return false;
            }


        });

        returnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: returnFalse");
            }
        });


        findViewById(R.id.disable_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果设置 enabled 为 false，OnTouchListener#onTouch 不会执行
                // 但是 onTouchEvent 还是会正常执行
                boolean enabled = returnTrue.isEnabled();
                returnTrue.setEnabled(!enabled);
                returnFalse.setEnabled(!enabled);
                String text = !enabled ? "TRUE" : "FALSE";
                ((Button)v).setText("enable: " + text);
            }
        });

        findViewById(R.id.clickable_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 此时 onTouchEvent() 返回 false，dispatchTouchEvent() 也返回 false
                // View 就不会再接收后续的事件，且所有的事件都会返回到父元素的 onTouchEvent() 中处理
                boolean enabled = returnTrue.isClickable();
                returnTrue.setClickable(!enabled);
                returnTrue.setLongClickable(!enabled);
                returnFalse.setClickable(!enabled);
                returnFalse.setLongClickable(!enabled);
                String text = !enabled ? "TRUE" : "FALSE";
                ((Button)v).setText("clickable: " + text);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}
