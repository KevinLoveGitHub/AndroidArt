package org.lovedev.chapter_2;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author Kevin
 * @data 2018/1/26
 */
public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        int pid = android.os.Process.myPid();//获取进程pid
        String processName = null;
        ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);//获取系统的ActivityManager服务
        for (ActivityManager.RunningAppProcessInfo appProcess : am.getRunningAppProcesses()){
            if(appProcess.pid == pid){
                processName = appProcess.processName;
                break;
            }
        }

        Log.d(TAG, "processName: " + processName);
    }


}
