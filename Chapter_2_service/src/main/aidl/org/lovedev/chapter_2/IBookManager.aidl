// IBookManager.aidl
package org.lovedev.chapter_2;

import org.lovedev.chapter_2.Book;
import org.lovedev.chapter_2.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
