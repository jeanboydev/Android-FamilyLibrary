package cn.edu.pku.app.familylibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by jeanboy on 2016/7/4.
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    /**
     * 初始化一些工具，耗时操作放到splash中
     */
    public void init(Context context) {
    }


}
