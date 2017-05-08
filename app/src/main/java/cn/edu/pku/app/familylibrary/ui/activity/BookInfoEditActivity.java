package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.model.Book;

public class BookInfoEditActivity extends BaseActivity {

    private Book book;

    @Override
    public Class getTag(Class clazz) {
        return BookInfoEditActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_info_edit;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            book = getIntent().getExtras().getParcelable(KEY_BOOK);
        }
        setToolsBarTitle(book == null ? R.string.title_add_book : R.string.title_edit_book).isTranslucent().setToolbarTopMargin().homeAsUp();
    }

    @Override
    public void initData() {

    }

    private static final String KEY_BOOK = "key_book";

    @SafeVarargs
    public static void goActivity(Activity context, Book book, Pair<View, String>... pairs) {
        Intent intent = new Intent(context, BookInfoEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BOOK, book);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context, pairs).toBundle());
    }
}
