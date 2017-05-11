package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;
import cn.edu.pku.app.familylibrary.utils.DateUtil;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class InfoAdapter extends RecyclerBaseAdapter<Record> {

    public InfoAdapter(@NonNull List<Record> dataList) {
        super(dataList, R.layout.item_info_list);
    }

    @Override
    public void convert(BaseViewHolder holder, Record record, int position) {
        if (record.getUser() != null) {
            holder.setText(R.id.tv_name, record.getUser().getRealName());
        }

        holder.setText(R.id.tv_time, DateUtil.dateFormat(record.getCreateTime()));
        holder.setText(R.id.tv_state, Constants.BOOK_OUT == record.getStatus() ? "在读" : "已还");
        ((TextView) holder.getView(R.id.tv_state)).setTextColor(Constants.BOOK_OUT == record.getStatus() ?
                holder.getConvertView().getResources().getColor(R.color.colorAccent) :
                holder.getConvertView().getResources().getColor(R.color.common_text));
    }
}
