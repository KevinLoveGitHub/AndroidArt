package org.lovedev.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import org.lovedev.chapter_2.IBinderPool;

/**
 * @author Kevin
 * @data 2018/4/19
 */
public enum  BinderPool {
    INSTANCE;

    public static final int BINDER_COMPUTE = 1;
    public static final int BINDER_SPEAK = 2;

    private IBinderPool mIBinderPool;

    public void init(Context context) {
        Context app = context.getApplicationContext();
        Intent service = new Intent();
        service.setAction("org.lovedev.binderPool");
        service.setPackage("org.lovedev.chapter_2");
        app.bindService(service, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinderPool = IBinderPool.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder queryBinder(int binderCode) {
        if (mIBinderPool == null) {
            throw new NullPointerException("please call init() first");
        }
        IBinder binder = null;
        try {
            binder = mIBinderPool.queryBinder(binderCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return binder;
    }
}
