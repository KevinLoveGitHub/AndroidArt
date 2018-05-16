package org.lovedev.chapter_2;

import android.os.RemoteException;

/**
 * @author Kevin
 * @data 2018/4/19
 */
public class ComputeIml extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
