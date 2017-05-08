package cn.edu.pku.app.familylibrary.constant;

/**
 * Created by jeanboy on 2017/5/8.
 */

public enum Gender {

    GIRL(0), BOY(1);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "value=" + value +
                '}';
    }
}
