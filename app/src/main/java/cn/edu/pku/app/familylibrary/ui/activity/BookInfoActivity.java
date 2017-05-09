package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.adapter.HomeAdapter;
import cn.edu.pku.app.familylibrary.adapter.InfoAdapter;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;

public class BookInfoActivity extends BaseActivity {


    private RecyclerView mRecyclerView;
    private List<Record> dataList = new ArrayList<>();
    private InfoAdapter infoAdapter;

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
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(new Record());
        }
        infoAdapter.notifyDataSetChanged();
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
