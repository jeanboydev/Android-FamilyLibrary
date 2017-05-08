package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.model.Book;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class BookListAdapter extends RecyclerBaseAdapter<Book> {


    private List<SwipeLayout> openedList = new ArrayList<>();

    public BookListAdapter(@NonNull List<Book> dataList) {
        super(dataList, R.layout.item_book_list);
    }

    @Override
    public void convert(BaseViewHolder holder, Book book, final int position) {

        SwipeLayout swipeLayout = holder.getView(R.id.swipe_layout);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                closeSwipeLayout();
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                openedList.add(layout);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        holder.getView(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSwipeLayout();
                if (onActionClickListener != null) {
                    onActionClickListener.onEdit(position);
                }
            }
        });

        holder.getView(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSwipeLayout();
                if (onActionClickListener != null) {
                    onActionClickListener.onDelete(position);
                }
            }
        });
    }

    @Override
    protected void setListener(final BaseViewHolder viewHolder) {
        viewHolder.getView(R.id.item_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openedList.size() > 0) {
                    closeSwipeLayout();
                    return;
                }
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });
    }

    public void closeSwipeLayout() {
        for (int i = 0; i < openedList.size(); i++) {
            openedList.get(i).close();
        }
        openedList.clear();
    }

    private OnActionClickListener onActionClickListener;

    public void setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.onActionClickListener = onActionClickListener;
    }

    public interface OnActionClickListener {

        void onEdit(int position);

        void onDelete(int position);
    }
}
