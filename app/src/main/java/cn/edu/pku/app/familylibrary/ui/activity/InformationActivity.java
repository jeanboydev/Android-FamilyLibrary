package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.constant.Constants;

public class InformationActivity extends BaseActivity {

    private TextView tv_book_count, tv_normal_book_count, tv_e_book_count, tv_reader_count, tv_out_count;

    @Override
    public Class getTag(Class clazz) {
        return InformationActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_infomation;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.item_readers).homeAsUp();

        tv_book_count = (TextView) findViewById(R.id.tv_book_count);
        tv_normal_book_count = (TextView) findViewById(R.id.tv_normal_book_count);
        tv_e_book_count = (TextView) findViewById(R.id.tv_e_book_count);
        tv_reader_count = (TextView) findViewById(R.id.tv_reader_count);
        tv_out_count = (TextView) findViewById(R.id.tv_out_count);

    }

    @Override
    public void initData() {
        tv_book_count.setText(String.valueOf(MainApplication.getInstance().getBookList().size()));
        tv_normal_book_count.setText(String.valueOf(MainApplication.getInstance().getBookByType(Constants.BOOK_NORMAL).size()));
        tv_e_book_count.setText(String.valueOf(MainApplication.getInstance().getBookByType(Constants.BOOK_E).size()));
        tv_reader_count.setText(String.valueOf(MainApplication.getInstance().getUserList().size()));
        tv_out_count.setText(String.valueOf(MainApplication.getInstance().getBookRecordMap().size()));
    }

    public static void goActivity(Activity context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }
}
