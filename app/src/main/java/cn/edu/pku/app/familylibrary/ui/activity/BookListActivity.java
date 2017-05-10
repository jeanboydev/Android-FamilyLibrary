package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.adapter.BookListAdapter;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.ui.dialog.DialogUtil;

public class BookListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    private List<Book> dataList = new ArrayList<>();
    private BookListAdapter bookListAdapter;


    private String[] operateItems = new String[]{"编辑", "删除"};

    @Override
    public Class getTag(Class clazz) {
        return BookListActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_list;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.item_books).isTranslucent().setToolbarTopMargin().homeAsUp();

        mRecyclerView = (RecyclerView) findViewById(R.id.list_container);

        bookListAdapter = new BookListAdapter(dataList);
        mRecyclerView.setAdapter(bookListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_space_line)));

        bookListAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                BookInfoActivity.goActivity(BookListActivity.this, dataList.get(position));
            }

            @Override
            public void onItemLongClick(View view, BaseViewHolder holder, final int position) {
                DialogUtil.showOperateDialog(BookListActivity.this, operateItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            BookInfoEditActivity.goActivity(BookListActivity.this, dataList.get(position));
                        } else {
                            DialogUtil.showWarningDialog(BookListActivity.this, R.string.msg_item_delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        MainApplication.getInstance().getBookList().remove(position);
                                        dataList.remove(position);
                                        bookListAdapter.notifyDataSetChanged();
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
        dataList.clear();
        dataList.addAll(MainApplication.getInstance().getBookList());
        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_add, menu);
        MenuItem searchItem = menu.findItem(R.id.action_add);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                BookInfoEditActivity.goActivity(BookListActivity.this, null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public static void goActivity(Activity context) {
        Intent intent = new Intent(context, BookListActivity.class);
        context.startActivity(intent);
    }

}
