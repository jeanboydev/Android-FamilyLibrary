package cn.edu.pku.app.familylibrary.constant;

/**
 * Created by jeanboy on 2017/5/8.
 */

public enum Status {

    OUT(0), IN(1);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
