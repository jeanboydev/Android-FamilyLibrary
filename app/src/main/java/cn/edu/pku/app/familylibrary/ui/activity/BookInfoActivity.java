package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.adapter.InfoAdapter;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;
import cn.edu.pku.app.familylibrary.ui.dialog.DialogUtil;

public class BookInfoActivity extends BaseActivity {


    private RecyclerView mRecyclerView;
    private List<Record> dataList = new ArrayList<>();
    private InfoAdapter infoAdapter;

    private Button btn_add, btn_look;

    private TextView tv_name_cover;
    private TextView tv_author_cover;
    private TextView tv_press_cover;

    private TextView tv_name;
    private TextView tv_author;
    private TextView tv_press;
    private TextView tv_number;
    private TextView tv_count;
    private TextView tv_e_book;
    private TextView tv_out;


    private String[] operateItems = new String[]{"编辑", "删除"};

    private Book book;

    @Override
    public Class getTag(Class clazz) {
        return BookInfoActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_info;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.title_info).homeAsUp().isTranslucent();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_container);

        infoAdapter = new InfoAdapter(dataList);
        mRecyclerView.setAdapter(infoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_space_line)));

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_look = (Button) findViewById(R.id.btn_look);
        tv_name_cover = (TextView) findViewById(R.id.tv_name_cover);
        tv_author_cover = (TextView) findViewById(R.id.tv_author_cover);
        tv_press_cover = (TextView) findViewById(R.id.tv_press_cover);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_press = (TextView) findViewById(R.id.tv_press);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_e_book = (TextView) findViewById(R.id.tv_e_book);
        tv_out = (TextView) findViewById(R.id.tv_out);

        btn_add.setVisibility(MainApplication.getInstance().isOnline() ? View.VISIBLE : View.GONE);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookRecordActivity.goActivity(BookInfoActivity.this, new Record(null, book));
            }
        });

        if (getIntent() != null && getIntent().getExtras() != null) {
            book = getIntent().getExtras().getParcelable(KEY_BOOK);
        }

        if (book == null) {
            this.finish();
            return;
        }
        tv_name_cover.setText(book.getName());
        tv_author_cover.setText(book.getAuthor());
        tv_press_cover.setText(book.getPress());

        tv_name.setText(book.getName());
        tv_author.setText(book.getAuthor());
        tv_press.setText(book.getPress());
        tv_number.setText(book.getNumber());
        tv_count.setText("剩余 " + book.getCount());
        tv_e_book.setVisibility(Constants.BOOK_E == book.getType() ? View.VISIBLE : View.GONE);
        btn_look.setVisibility(Constants.BOOK_E == book.getType() ? View.VISIBLE : View.GONE);
        tv_out.setVisibility(Constants.BOOK_IN != book.getStatus() ? View.VISIBLE : View.GONE);


        infoAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {

            }

            @Override
            public void onItemLongClick(View view, BaseViewHolder holder, final int position) {
                DialogUtil.showOperateDialog(BookInfoActivity.this, operateItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            BookRecordActivity.goActivity(BookInfoActivity.this, dataList.get(position));
                        } else {
                            DialogUtil.showWarningDialog(BookInfoActivity.this, R.string.msg_item_delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        MainApplication.getInstance().removeBookRecord(dataList.get(position));
                                        dataList.remove(position);
                                        infoAdapter.notifyDataSetChanged();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        List<Record> bookRecordList = MainApplication.getInstance().getBookRecordList(book);
        if (bookRecordList != null) {
            dataList.clear();
            dataList.addAll(bookRecordList);
            infoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private static final String KEY_BOOK = "key_book";

    public static void goActivity(Activity context, Book book) {
        Intent intent = new Intent(context, BookInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BOOK, book);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
