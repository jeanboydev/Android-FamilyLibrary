package cn.edu.pku.app.familylibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import cn.edu.pku.app.familylibrary.constant.Gender;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class User implements Parcelable{

    private String realName;
    private Gender gender;
    private String contact;
    private String note;
    private long createTime;

    public User() {
    }

    public User(String realName, Gender gender, String contact, String note) {
        this.realName = realName;
        this.gender = gender;
        this.contact = contact;
        this.note = note;
        this.createTime = System.currentTimeMillis();
    }

    protected User(Parcel in) {
        realName = in.readString();
        contact = in.readString();
        note = in.readString();
        createTime = in.readLong();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "realName='" + realName + '\'' +
                ", gender=" + gender +
                ", contact='" + contact + '\'' +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realName);
        dest.writeString(contact);
        dest.writeString(note);
        dest.writeLong(createTime);
    }
}
