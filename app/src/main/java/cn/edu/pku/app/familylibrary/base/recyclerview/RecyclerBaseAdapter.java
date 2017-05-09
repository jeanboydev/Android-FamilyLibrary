package cn.edu.pku.app.familylibrary.base.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jeanboy on 2016/8/10.
 */
public abstract class RecyclerBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    private List<T> dataList;
    private int itemLayoutId = 0;

    public RecyclerBaseAdapter(@NonNull List<T> dataList, int itemLayoutId) {
        this.dataList = dataList;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(getLayoutView(parent, itemLayoutId));
        setListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, dataList.get(position), holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public View getLayoutView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    /**
     * 设置
     *
     * @param viewHolder
     */
    protected void setListener(final BaseViewHolder viewHolder) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return true;
            }
        });
    }


    public abstract void convert(BaseViewHolder holder, T t, int position);


    protected OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemClick(View view, BaseViewHolder holder, int position);

        void onItemLongClick(View view, BaseViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
