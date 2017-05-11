package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class SearchAdapter extends RecyclerBaseAdapter<Book> {

    public SearchAdapter(@NonNull List<Book> dataList) {
        super(dataList, R.layout.item_home_list);
    }

    @Override
    public void convert(BaseViewHolder holder, Book book, int position) {
        holder.setText(R.id.tv_name_cover, book.getName());
        holder.setText(R.id.tv_name, book.getName());
        holder.setText(R.id.tv_author_cover, book.getAuthor());
        holder.setText(R.id.tv_author, book.getAuthor());
        holder.setText(R.id.tv_press_cover, book.getPress());
        holder.setText(R.id.tv_count, "剩余 " + book.getCount());
        holder.setVisible(R.id.tv_e_book, Constants.BOOK_E == book.getType());
        holder.setVisible(R.id.tv_out, Constants.BOOK_IN != book.getStatus());
    }
}
