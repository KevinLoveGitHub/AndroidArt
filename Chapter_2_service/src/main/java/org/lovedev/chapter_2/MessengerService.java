package org.lovedev.chapter_2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * @author Kevin
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";
    private boolean threadDisable;
    private int count;

    public MessengerService() {

    }

    // 2. 自定义 Handler 对象接收并处理消息
    private static final class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "MessengerService received message: " + msg.getData());
        }
    }

    // 1. 创建 Messenger 对象
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public void onCreate() {
        super.onCreate();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                while(!threadDisable) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    count++;
//                    Log.d(TAG, "count is " + count);
//                }
//            }
//        }
//        ).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
