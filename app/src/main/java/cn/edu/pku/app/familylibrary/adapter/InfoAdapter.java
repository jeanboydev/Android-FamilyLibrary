package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class InfoAdapter extends RecyclerBaseAdapter<Record> {

    public InfoAdapter(@NonNull List<Record> dataList) {
        super(dataList, R.layout.item_info_list);
    }

    @Override
    public void convert(BaseViewHolder holder, Record record, int position) {

    }
}
