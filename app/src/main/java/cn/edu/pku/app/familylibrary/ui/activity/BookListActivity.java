package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
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
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.model.Book;

public class BookListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    private List<Book> dataList = new ArrayList<>();
    private BookListAdapter bookListAdapter;

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
                if (bookListAdapter != null) {
                    bookListAdapter.closeSwipeLayout();
                }
                BookInfoActivity.goActivity(BookListActivity.this, dataList.get(position));
            }
        });

        bookListAdapter.setOnActionClickListener(new BookListAdapter.OnActionClickListener() {
            @Override
            public void onEdit(int position) {
                BookInfoEditActivity.goActivity(BookListActivity.this, dataList.get(position));
            }

            @Override
            public void onDelete(int position) {

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (bookListAdapter != null) {
                    bookListAdapter.closeSwipeLayout();
                }
            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(new Book());
        }
        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_add, menu);
        MenuItem searchItem = menu.findItem(R.id.action_add);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e(TAG, "===============");
                BookInfoEditActivity.goActivity(BookListActivity.this, null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @SafeVarargs
    public static void goActivity(Activity context, Pair<View, String>... pairs) {
        Intent intent = new Intent(context, BookListActivity.class);
        ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context, pairs).toBundle());
    }

}
