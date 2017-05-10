package cn.edu.pku.app.familylibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class Record implements Parcelable{

    private User user;
    private Book book;
    private int status;
    private long createTime;

    public Record() {
    }

    public Record(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Record(User user, Book book, int status) {
        this.user = user;
        this.book = book;
        this.status = status;
        this.createTime = System.currentTimeMillis();
    }

    protected Record(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        book = in.readParcelable(Book.class.getClassLoader());
        status = in.readInt();
        createTime = in.readLong();
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        @Override
        public Record createFromParcel(Parcel in) {
            return new Record(in);
        }

        @Override
        public Record[] newArray(int size) {
            return new Record[size];
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "user=" + user +
                ", book=" + book +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeParcelable(book, flags);
        dest.writeInt(status);
        dest.writeLong(createTime);
    }
}
