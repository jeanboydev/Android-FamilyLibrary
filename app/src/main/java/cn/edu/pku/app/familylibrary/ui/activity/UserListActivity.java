package cn.edu.pku.app.familylibrary.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.adapter.BookListAdapter;
import cn.edu.pku.app.familylibrary.adapter.UserListAdapter;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.constant.Constants;
import cn.edu.pku.app.familylibrary.model.Book;
import cn.edu.pku.app.familylibrary.model.Record;
import cn.edu.pku.app.familylibrary.model.User;
import cn.edu.pku.app.familylibrary.ui.dialog.DialogUtil;

public class UserListActivity extends BaseActivity {


    private RecyclerView mRecyclerView;

    private List<User> dataList = new ArrayList<>();
    private UserListAdapter userListAdapter;

    private String[] operateItems = new String[]{"编辑", "删除"};

    private Book bookRecord;

    @Override
    public Class getTag(Class clazz) {
        return UserListActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.item_readers).isTranslucent().setToolbarTopMargin().homeAsUp();


        if (getIntent() != null && getIntent().getExtras() != null) {
            bookRecord = getIntent().getExtras().getParcelable(Constants.RESULT_USER);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list_container);

        userListAdapter = new UserListAdapter(dataList);
        mRecyclerView.setAdapter(userListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_space_line)));

        userListAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                if (bookRecord != null) {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.RESULT_USER, dataList.get(position));
                    setResult(RESULT_OK);
                    UserListActivity.this.finish();
                }
            }

            @Override
            public void onItemLongClick(View view, BaseViewHolder holder, final int position) {
                DialogUtil.showOperateDialog(UserListActivity.this, operateItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            UserInfoEditActivity.goActivity(UserListActivity.this, dataList.get(position));
                        } else {
                            DialogUtil.showWarningDialog(UserListActivity.this, R.string.msg_item_delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        MainApplication.getInstance().getUserList().remove(position);
                                        dataList.remove(position);
                                        userListAdapter.notifyDataSetChanged();
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


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void initData() {
        dataList.clear();
        dataList.addAll(MainApplication.getInstance().getUserList());
        userListAdapter.notifyDataSetChanged();
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
                UserInfoEditActivity.goActivity(UserListActivity.this, null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public static void goActivity(Activity context) {
        Intent intent = new Intent(context, UserListActivity.class);
        context.startActivity(intent);
    }

}
