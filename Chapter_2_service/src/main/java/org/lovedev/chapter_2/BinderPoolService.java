package org.lovedev.chapter_2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * @author Kevin
 * @data 2018/4/19
 */
public class BinderPoolService extends Service {

    private final int BINDER_COMPUTE = 1;
    private final int BINDER_SPEAK = 2;

    private Binder mBinder = new IBinderPool.Stub() {
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder;
            switch (binderCode) {
                case BINDER_COMPUTE:
                    binder = new ComputeIml();
                break;
                case BINDER_SPEAK:
                    binder = new SpeakIml();
                    break;
                default:
                    binder = null;
                    break;
            }
            return binder;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
