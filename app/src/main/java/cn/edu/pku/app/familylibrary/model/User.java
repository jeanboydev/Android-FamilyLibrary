package cn.edu.pku.app.familylibrary.model;

import cn.edu.pku.app.familylibrary.constant.Gender;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class User {

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
}
