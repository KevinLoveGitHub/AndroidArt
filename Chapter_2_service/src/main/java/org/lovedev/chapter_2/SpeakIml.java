package org.lovedev.chapter_2;

import android.os.RemoteException;
import android.util.Log;

/**
 * @author Kevin
 * @data 2018/4/19
 */
public class SpeakIml extends ISpeak.Stub {
    private static final String TAG = "SpeakIml";

    @Override
    public void speak(String message) throws RemoteException {
        Log.d(TAG, "speak: " + message);
    }
}
