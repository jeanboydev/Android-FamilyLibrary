package cn.edu.pku.app.familylibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import cn.edu.pku.app.familylibrary.constant.Constants;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class Book implements Parcelable {

    private String name;//书名
    private String number;//书号
    private String author;//作者
    private String press;//出版社
    private int type;//类别
    private int status;
    private int count;
    private long createTime;

    public Book() {
        this.status = Constants.BOOK_IN;
        this.createTime = System.currentTimeMillis();
    }

    public Book(String name, String number, String author, String press, int type, int count) {
        this.name = name;
        this.number = number;
        this.author = author;
        this.press = press;
        this.type = type;
        this.count = count;
        this.status = Constants.BOOK_IN;
        this.createTime = System.currentTimeMillis();
    }


    protected Book(Parcel in) {
        name = in.readString();
        number = in.readString();
        author = in.readString();
        press = in.readString();
        type = in.readInt();
        status = in.readInt();
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
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
                ", status=" + status +
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
        dest.writeInt(type);
        dest.writeInt(status);
        dest.writeInt(count);
        dest.writeLong(createTime);
    }
}
