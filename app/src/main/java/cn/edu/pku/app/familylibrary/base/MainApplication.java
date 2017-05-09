package cn.edu.pku.app.familylibrary.base;

import android.app.Application;
import android.content.Context;

import cn.edu.pku.app.familylibrary.model.Admin;

/**
 * Created by jeanboy on 2016/7/4.
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

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

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    
    public void init(Context context) {

    }


}
