package org.lovedev.chapter_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.view);
        mView.post(new Runnable() {
            @Override
            public void run() {
                getViewLayout("post");
            }
        });

        ViewTreeObserver viewTreeObserver = mView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewLayout("onGlobalLayout");
            }
        });

        int width = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.EXACTLY);
        int height = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        mView.measure(width, height);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getViewLayout("onWindowFocusChanged");
    }

    private void getViewLayout(String tag) {
        int measuredHeight = mView.getMeasuredHeight();
        int measuredWidth = mView.getMeasuredWidth();
        Log.d(TAG, tag + " onWindowFocusChanged: height" + measuredHeight + " width: " + measuredWidth);
    }
}
