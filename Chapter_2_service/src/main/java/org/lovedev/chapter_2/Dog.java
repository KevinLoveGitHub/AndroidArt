package org.lovedev.chapter_2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Kevin
 * @data 2018/1/28
 */
public class Dog implements Parcelable{
    private String name;
    private int age;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public Dog() {}

    protected Dog(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel source) {return new Dog(source);}

        @Override
        public Dog[] newArray(int size) {return new Dog[size];}
    };
}
