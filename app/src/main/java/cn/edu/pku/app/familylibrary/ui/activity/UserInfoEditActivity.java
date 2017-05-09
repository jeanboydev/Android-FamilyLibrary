package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.User;

public class UserInfoEditActivity extends BaseActivity {

    @Override
    public Class getTag(Class clazz) {
        return UserInfoEditActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info_edit;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    private static final String KEY_USER = "key_user";

    public static void goActivity(Activity context, User user) {
        Intent intent = new Intent(context, UserInfoEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
