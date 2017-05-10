package cn.edu.pku.app.familylibrary.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.model.User;

/**
 * Created by jeanboy on 2017/5/8.
 */

public class UserListAdapter extends RecyclerBaseAdapter<User> {


    public UserListAdapter(@NonNull List<User> dataList) {
        super(dataList, R.layout.item_user_list);
    }

    @Override
    public void convert(BaseViewHolder holder, User user, final int position) {
        holder.setText(R.id.tv_name,user.getRealName());
    }
}
