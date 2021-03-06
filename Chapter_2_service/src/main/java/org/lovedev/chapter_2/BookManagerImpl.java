package org.lovedev.chapter_2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * 该类就是抽取出来的 Stub，实现了先前声明的 AIDL 性质的接口
 * @author Kevin
 * @data 2018/2/6
 */
public class BookManagerImpl extends Binder implements IIBookManager{

    private static final String TAG = "BookManagerImpl";

    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IIBookManager asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof org.lovedev.chapter_2.IBookManager))) {
            return ((IIBookManager) iin);
        }
        return new BookManagerImpl.Proxy(obj);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        Log.i(TAG, "getBookList");
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.i(TAG, "addBook: " + book.toString());
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                List<Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }


    private static class Proxy implements IIBookManager {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        /**
         * 当客户端调用该方法时，首先创建该方法所需的 _data 和 _reply，然后把该方法的参数写入到 _data 中
         * 接着调用 transact 方法发起 RPC（远程调用）请求，同时当前线程挂起
         * 然后服务端的 onTransact 方法会被调用，直到 RPC 过程返回后，当前线程继续执行，从 _reply 中取出 RPC 过程的返回结果
         * 最后返回 _reply 中的数据
         */
        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            List<Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

}
