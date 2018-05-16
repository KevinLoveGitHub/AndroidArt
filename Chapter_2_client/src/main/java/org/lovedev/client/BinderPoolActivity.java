package org.lovedev.client;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.lovedev.chapter_2.ICompute;
import org.lovedev.chapter_2.ISpeak;

/**
 * @author Kevin
 */
public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "BinderPoolActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        BinderPool.INSTANCE.init(this);
    }

    public void compute(View view) {
        IBinder binder = BinderPool.INSTANCE.queryBinder(BinderPool.BINDER_COMPUTE);
        ICompute iCompute = ICompute.Stub.asInterface(binder);
        try {
            int add = iCompute.add(1, 4);
            Log.d(TAG, "compute: " + add);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void speak(View view) {
        IBinder binder = BinderPool.INSTANCE.queryBinder(BinderPool.BINDER_SPEAK);
        ISpeak iSpeak = ISpeak.Stub.asInterface(binder);
        try {
            iSpeak.speak("speak hello world");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
