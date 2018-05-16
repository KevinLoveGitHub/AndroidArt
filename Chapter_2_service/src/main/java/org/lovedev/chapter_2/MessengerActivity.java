package org.lovedev.chapter_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MessengerActivity extends AppCompatActivity {


    private static final String TAG = "MessengerActivity";
    private Intent mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        mService = new Intent();
        mService.setAction("org.lovedev.messenger");
        // Android 5.0 以上必须执行该函数，否则会报 Service Intent must be explicit 错误
        mService.setPackage(getPackageName());
        bindService(mService, mConnection, Context.BIND_AUTO_CREATE);
    }

    private Messenger mMessenger;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: " + name);
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: " + name);
        }
    };



    public void send(View view) {
        Message message = Message.obtain(null, 1);
        Bundle data = new Bundle();
        data.putString("data", "MessengerActivity's data");
        message.setData(data);
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
