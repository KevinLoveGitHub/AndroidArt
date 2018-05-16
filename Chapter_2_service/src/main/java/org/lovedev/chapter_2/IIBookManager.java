package org.lovedev.chapter_2;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * 查看由 IBookManager.aidl 文件生成的 @{@link IBookManager } 中的 Stub 类，可以发现该类实现了 IBookManager 接口
 * 所以可以把该类单独抽取出来，首先需要声明一个 AIDL 性质的接口来让 Stub 类实现
 * @author Kevin
 * @data 2018/2/6
 */
public interface IIBookManager extends IInterface {
    static final String DESCRIPTOR = "org.lovedev.chapter_2.IIBookManager";

    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION);
    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 1);
    public List<Book> getBookList() throws RemoteException;
    public void addBook(Book book) throws RemoteException;

}
