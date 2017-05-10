package cn.edu.pku.app.familylibrary.base;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Admin;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;
import cn.edu.pku.app.familylibrary.model.User;

/**
 * Created by jeanboy on 2016/7/4.
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Record> recordList = new ArrayList<>();
    private Map<String, List<Record>> bookRecordMap = new HashMap<>();//book number,Record

    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isOnline() {
        return admin != null;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addBookRecord(Record record) {
        if (record.getBook() == null) return;
        List<Record> recordList = getBookRecordList(record.getBook());
        if (recordList == null) {
            recordList = new ArrayList<>();
        }
        recordList.add(record);
        bookRecordMap.put(record.getBook().getNumber(), recordList);
    }

    public void removeBookRecord(Record record) {
        List<Record> bookRecordList = getBookRecordList(record.getBook());
        if (bookRecordList != null) {
            for (int i = 0; i < bookRecordList.size(); i++) {
                Record r = bookRecordList.get(i);
                try {
                    if (r.getUser().getRealName().equals(record.getUser().getRealName())
                            && r.getBook().getNumber().equals(record.getBook().getNumber())) {
                        bookRecordList.remove(i);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Record> getBookRecordList(Book book) {
        return bookRecordMap.get(book.getNumber());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init(getApplicationContext());
    }

    public void init(Context context) {
        Book book = new Book("Effective Java", "B0000010", "[美] Joshua Bloch ", "机械工业出版社", Constants.BOOK_NORMAL, 1);
        bookList.add(book);
        bookList.add(new Book("福尔摩斯探案全集", "B0000009", "[英] 阿·柯南道尔", "群众出版社", Constants.BOOK_E, 3));
        bookList.add(new Book("小王子", "B0000001", "[法] 圣埃克苏佩里", "人民文学出版社", Constants.BOOK_NORMAL, 1));
        bookList.add(new Book("围城", "B0000002", "钱钟书", "人民文学出版社", Constants.BOOK_NORMAL, 1));
        bookList.add(new Book("活着", "B0000003", "余华", " 南海出版公司", Constants.BOOK_NORMAL, 1));
        bookList.add(new Book("追风筝的人", "B0000004", "[美] 卡勒德·胡赛尼", " 上海人民出版社", Constants.BOOK_NORMAL, 1));
        bookList.add(new Book("三体", "B0000006", "刘慈欣", " 重庆出版社", Constants.BOOK_E, 3));
        bookList.add(new Book("红楼梦", "B0000005", "[清] 曹雪芹", " 人民文学出版社", Constants.BOOK_NORMAL, 1));
        bookList.add(new Book("笑傲江湖", "B0000008", "金庸", "人民文学出版社", Constants.BOOK_NORMAL, 4));
        bookList.add(new Book("百年孤独", "B0000007", "[哥伦比亚] 加西亚·马尔克斯", "南海出版公司", Constants.BOOK_E, 1));


        User user1 = new User("测试读者1", Constants.GENDER_BOY, "16666666666", "内置测试读者用户1");
        User user2 = new User("测试读者2", Constants.GENDER_GIRL, "17777777777", "内置测试读者用户2");
        userList.add(user1);
        userList.add(user2);

        Record testRecord1 = new Record(user1, book, Constants.BOOK_IN);
        Record testRecord2 = new Record(user2, book, Constants.BOOK_OUT);

        addBookRecord(testRecord1);
        addBookRecord(testRecord2);
    }


}
