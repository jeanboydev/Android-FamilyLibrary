package cn.edu.pku.app.familylibrary.constant;

/**
 * Created by jeanboy on 2017/5/8.
 */

public enum Type {

    BOOK(0), E_BOOK(1);

    private int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
