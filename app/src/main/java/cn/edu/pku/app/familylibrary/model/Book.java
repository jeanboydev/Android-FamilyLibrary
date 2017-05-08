package cn.edu.pku.app.familylibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import cn.edu.pku.app.familylibrary.constant.Type;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class Book implements Parcelable{

    private String name;//书名
    private String number;//书号
    private String author;//作者
    private String press;//出版社
    private Type type;//类别
    private int count;
    private long createTime;

    public Book() {
    }

    public Book(String name, String number, String author, String press, Type type, int count) {
        this.name = name;
        this.number = number;
        this.author = author;
        this.press = press;
        this.type = type;
        this.count = count;
        this.createTime = System.currentTimeMillis();
    }

    protected Book(Parcel in) {
        name = in.readString();
        number = in.readString();
        author = in.readString();
        press = in.readString();
        count = in.readInt();
        createTime = in.readLong();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isCanRead() {
        return this.count > 0;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", type=" + type +
                ", count=" + count +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(author);
        dest.writeString(press);
        dest.writeInt(count);
        dest.writeLong(createTime);
    }
}
