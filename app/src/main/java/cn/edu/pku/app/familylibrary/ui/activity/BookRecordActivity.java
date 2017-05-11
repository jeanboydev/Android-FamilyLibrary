package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;
import cn.edu.pku.app.familylibrary.model.User;

public class BookRecordActivity extends BaseActivity {

    private TextView tv_book_name, tv_username;
    private Spinner book_state;

    private Record record;

    private boolean isAdded = false;

    @Override
    public Class getTag(Class clazz) {
        return BookRecordActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_record;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.item_record).isTranslucent().setToolbarTopMargin().homeAsUp();

        if (getIntent() != null && getIntent().getExtras() != null) {
            record = getIntent().getExtras().getParcelable(KEY_RECORD);
        }

        if (record == null) {
            this.finish();
            return;
        }

        isAdded = record.getUser() == null;

        tv_book_name = (TextView) findViewById(R.id.tv_book_name);
        tv_username = (TextView) findViewById(R.id.tv_username);
        book_state = (Spinner) findViewById(R.id.book_state);
    }

    @Override
    public void initData() {
        if (record.getBook() != null) {
            tv_book_name.setText(record.getBook().getName());
        }

        if (record.getUser() != null) {
            tv_username.setText(record.getUser().getRealName());
        }

        if (Constants.BOOK_IN == record.getStatus()) {
            book_state.setSelection(1, true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && Constants.RESULT_SELECT_USER == requestCode) {
            if (data != null) {
                User selectUser = data.getExtras().getParcelable(Constants.RESULT_USER);
                if (selectUser != null) {
                    record.setUser(selectUser);
                    tv_username.setText(selectUser.getRealName());
                }
            }
        }
    }

    private static final String KEY_RECORD = "key_record";

    public static void goActivity(Activity context, Record record) {
        Intent intent = new Intent(context, BookRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_RECORD, record);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void addReader(View view) {
        Intent intent = new Intent(BookRecordActivity.this, UserListActivity.class);
        intent.putExtra(Constants.RESULT_USER, new Book());
        startActivityForResult(intent, Constants.RESULT_SELECT_USER);
    }

    public void toSubmit(View view) {
        record.setStatus(book_state.getSelectedItemPosition() == 0 ? Constants.BOOK_OUT : Constants.BOOK_IN);
        record.getBook().setStatus(record.getStatus());
        if (isAdded) {
            MainApplication.getInstance().addBookRecord(record);
        } else {
            MainApplication.getInstance().updateBookRecord(record);
        }
        this.finish();
    }
}
