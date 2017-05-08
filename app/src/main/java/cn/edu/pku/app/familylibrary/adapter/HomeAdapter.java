package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.model.Book;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class HomeAdapter extends RecyclerBaseAdapter<Book> {

    public HomeAdapter(@NonNull List<Book> dataList) {
        super(dataList, R.layout.item_home_list);
    }

    @Override
    public void convert(BaseViewHolder holder, Book book, int position) {

    }
}
