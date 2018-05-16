package org.lovedev.chapter_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Kevin
 * @data 2018/4/18
 */
public class BookManagerService extends Service {

    private static final String TAG = "BookManagerService";
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();
    private AtomicBoolean mIsServiceDestroy = new AtomicBoolean();

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Hello"));
        mBookList.add(new Book(2, "World"));
        new Thread(new ServiceWorker()).start();
    }

    private IBinder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
            //            if (mListenerList.contains(listener)) {
            //                Log.d(TAG, "registerListener: has exists");
            //            } else {
            //                mListenerList.add(listener);
            //            }

            mListenerList.beginBroadcast();
            Log.d(TAG, "registerListener: listener size " + mListenerList.getRegisteredCallbackCount());
            mListenerList.finishBroadcast();
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
            //            if (mListenerList.contains(listener)) {
            //                mListenerList.remove(listener);
            //                Log.d(TAG, "unRegisterListener: unRegisterListener success");
            //            } else {
            //                Log.d(TAG, "unRegisterListener: can't found listener");
            //            }
            mListenerList.beginBroadcast();
            Log.d(TAG, "unRegisterListener: listener size " + mListenerList.getRegisteredCallbackCount());
            mListenerList.finishBroadcast();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroy.set(true);
        super.onDestroy();

    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestroy.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new Book-" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        int count = mListenerList.beginBroadcast();
        for (int i = 0; i < count; i++) {
            IOnNewBookArrivedListener broadcastItem = mListenerList.getBroadcastItem(i);
            if (broadcastItem != null) {
                broadcastItem.onNewBookArrived(book);
            }
        }
        //        for (int i = 0; i < mListenerList.size(); i++) {
        //            IOnNewBookArrivedListener iOnNewBookArrivedListener = mListenerList.get(i);
        //            iOnNewBookArrivedListener.onNewBookArrived(book);
        //        }
        mListenerList.finishBroadcast();
    }

}
