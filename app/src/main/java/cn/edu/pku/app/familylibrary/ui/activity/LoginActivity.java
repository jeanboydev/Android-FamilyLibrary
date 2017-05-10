package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.constant.AppConfig;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Admin;

public class LoginActivity extends BaseActivity {

    private EditText et_username, et_password;

    @Override
    public Class getTag(Class clazz) {
        return LoginActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.title_login).homeAsUp();
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    @Override
    public void initData() {

    }

    public static void goActivity(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void toLogin(View view) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString();
        if (AppConfig.ADMIN_USERNAME.equals(username) && AppConfig.ADMIN_PASSWORD.equals(password)) {
            Admin admin = new Admin("管理员", Constants.GENDER_BOY, "18000000000", "管理员", username, password);
            MainApplication.getInstance().setAdmin(admin);
            this.finish();
        } else {
            showToastMessage("用户名或密码错误！");
        }
    }

}
