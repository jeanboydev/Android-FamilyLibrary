package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.User;

public class UserInfoEditActivity extends BaseActivity {

    private User user;

    private EditText et_name, et_concat, et_note;
    private Spinner user_gender;

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
        if (getIntent() != null && getIntent().getExtras() != null) {
            user = getIntent().getExtras().getParcelable(KEY_USER);
        }
        setToolsBarTitle(user == null ? R.string.title_add_user : R.string.title_edit_user).isTranslucent().setToolbarTopMargin().homeAsUp();

        et_name = (EditText) findViewById(R.id.et_name);
        et_concat = (EditText) findViewById(R.id.et_concat);
        et_note = (EditText) findViewById(R.id.et_note);
        user_gender = (Spinner) findViewById(R.id.user_gender);

    }

    @Override
    public void initData() {
        if (user == null) return;
        et_name.setText(user.getRealName());
        et_concat.setText(user.getContact());
        et_note.setText(user.getNote());
        if (Constants.GENDER_GIRL == user.getGender()) {
            user_gender.setSelection(1, true);
        }

    }

    private static final String KEY_USER = "key_user";

    public static void goActivity(Activity context, User user) {
        Intent intent = new Intent(context, UserInfoEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void toSubmit(View view) {
        boolean isAdded = false;
        if (user == null) {
            user = new User();
            isAdded = true;
        }

        user.setRealName(et_name.getText().toString());
        user.setContact(et_concat.getText().toString());
        user.setNote(et_note.getText().toString());
        user.setGender(user_gender.getSelectedItemPosition() == 0 ? Constants.GENDER_BOY : Constants.GENDER_GIRL);
        if (isAdded) {
            MainApplication.getInstance().getUserList().add(0, user);
        } else {
            for (User u : MainApplication.getInstance().getUserList()) {
                if (u.getRealName().equals(user.getRealName())) {
                    u.setRealName(user.getRealName());
                    u.setContact(user.getContact());
                    u.setNote(user.getNote());
                    u.setGender(user.getGender());
                    u.setCreateTime(user.getCreateTime());
                }
            }
        }
        this.finish();
    }
}
