package cn.edu.pku.app.familylibrary.model;

import cn.edu.pku.app.familylibrary.constant.Status;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class Record {

    private User user;
    private Book book;
    private Status status;
    private long createTime;

    public Record() {
    }

    public Record(User user, Book book, Status status) {
        this.user = user;
        this.book = book;
        this.status = status;
        this.createTime = System.currentTimeMillis();
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
