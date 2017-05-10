package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;

public class BookInfoEditActivity extends BaseActivity {

    private Book book;

    private EditText et_name, et_author, et_press, et_number, et_count;
    private Spinner book_type;

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

        et_name = (EditText) findViewById(R.id.et_name);
        et_author = (EditText) findViewById(R.id.et_author);
        et_press = (EditText) findViewById(R.id.et_press);
        et_number = (EditText) findViewById(R.id.et_number);
        et_count = (EditText) findViewById(R.id.et_count);
        book_type = (Spinner) findViewById(R.id.book_type);

    }

    @Override
    public void initData() {
        if (book == null) return;
        et_name.setText(book.getName());
        et_author.setText(book.getAuthor());
        et_press.setText(book.getPress());
        et_number.setText(book.getNumber());
        et_count.setText(book.getCount());
        if (Constants.BOOK_E == book.getType()) {
            book_type.setSelection(1, true);
        }
    }

    private static final String KEY_BOOK = "key_book";

    public static void goActivity(Activity context, Book book) {
        Intent intent = new Intent(context, BookInfoEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BOOK, book);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void toSubmit(View view) {
        boolean isAdded = false;
        if (book == null) {
            book = new Book();
            isAdded = true;
        }
        book.setName(et_name.getText().toString());
        book.setAuthor(et_author.getText().toString());
        book.setPress(et_press.getText().toString());
        book.setNumber(et_number.getText().toString());
        book.setCount(Integer.valueOf(et_count.getText().toString()));
        book.setType(book_type.getSelectedItemPosition() == 0 ? Constants.BOOK_NORMAL : Constants.BOOK_E);
        if (isAdded) {
            book.setStatus(Constants.BOOK_IN);
            MainApplication.getInstance().getBookList().add(0, book);
        }else {
            for (Book b : MainApplication.getInstance().getBookList()) {
                if (b.getNumber().equals(book.getNumber())) {
                    b.setCount(book.getCount());
                    b.setAuthor(book.getAuthor());
                    b.setCreateTime(book.getCreateTime());
                    b.setName(book.getName());
                    b.setPress(book.getPress());
                    b.setStatus(book.getStatus());
                    b.setType(book.getType());
                }
            }
        }
        this.finish();
    }
}
