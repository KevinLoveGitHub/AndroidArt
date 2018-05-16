package org.lovedev.chapter_1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (savedInstanceState != null) {
            String string = savedInstanceState.getString("string");
            Log.i(TAG, "onRestoreInstanceState: " + string);
        }
        setContentView(R.layout.activity_second);
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        Log.d(TAG, "当前任务栈ID：" + info.id);
    }

    public void finish(View view) {
        this.finish();
    }

    public void navigate(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void launchMyself(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("time", System.currentTimeMillis());
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // singleTask singleTop singleInstance 时回调该函数
        long time = intent.getLongExtra("time", 0);
        Log.d(TAG, "onNewIntent: " + time);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // 需要在 AndroidManifest.xml 中设置 activity 的 persistableMode 属性为 persistAcrossReboots，才能回调该 onCreate 方法
//        Log.i(TAG, "onCreate2");
        if (savedInstanceState != null) {
//            Log.i(TAG, "savedInstanceState: " + savedInstanceState.toString());
        }

        if (persistentState != null) {
//            Log.i(TAG, "persistentState: " + persistentState.toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String value = "Hello World";
        outState.putString("string", value);
        Log.i(TAG, "onSaveInstanceState: " + value);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String string = savedInstanceState.getString("string");
        Log.i(TAG, "onRestoreInstanceState: " + string);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
