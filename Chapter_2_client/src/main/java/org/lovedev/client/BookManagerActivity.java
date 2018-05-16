package org.lovedev.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.lovedev.chapter_2.Book;
import org.lovedev.chapter_2.IBookManager;
import org.lovedev.chapter_2.IOnNewBookArrivedListener;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BookManagerActivity";
    private IBookManager mRemoteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent service = new Intent();
        service.setAction("org.lovedev.book");
        service.setPackage("org.lovedev.chapter_2");
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private IOnNewBookArrivedListener.Stub mListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.d(TAG, "onNewBookArrived: " + newBook.toString());
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            String name = Thread.currentThread().getName();
            Log.d(TAG, "binderDied: " + name);
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);

            try {
                mRemoteManager = bookManager;
                List<Book> bookList = bookManager.getBookList();
                // bookList 的类型是 ArrayList，服务端的是 CopyAndWriteArrayList
                // 这样的原因是因为 Binder 把服务端的数据封装为 ArrayList 传递给了客户端
                Log.d(TAG, "onServiceConnected: list type " + bookList.getClass());
                Log.d(TAG, "onServiceConnected: list size " + bookList.size());

                bookManager.addBook(new Book(3, "3"));
                bookList = bookManager.getBookList();
                Log.d(TAG, "onServiceConnected: list size " + bookList.size());
                service.linkToDeath(mDeathRecipient, 0);
                bookManager.registerListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            String threadName = Thread.currentThread().getName();
            Log.d(TAG, "binderDied: " + threadName);
        }
    };

    @Override
    protected void onDestroy() {
        if (mRemoteManager != null && mRemoteManager.asBinder().isBinderAlive()) {
            try {
                mRemoteManager.unRegisterListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
