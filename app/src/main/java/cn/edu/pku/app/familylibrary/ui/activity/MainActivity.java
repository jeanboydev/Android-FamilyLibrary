package cn.edu.pku.app.familylibrary.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.app.familylibrary.R;
import cn.edu.pku.app.familylibrary.adapter.HomeAdapter;
import cn.edu.pku.app.familylibrary.base.BaseActivity;
import cn.edu.pku.app.familylibrary.base.MainApplication;
import cn.edu.pku.app.familylibrary.base.recyclerview.BaseViewHolder;
import cn.edu.pku.app.familylibrary.base.recyclerview.RecyclerBaseAdapter;
import cn.edu.pku.app.familylibrary.base.recyclerview.decoration.SpaceItemDecoration;
import cn.edu.pku.app.familylibrary.model.Book;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private View headerLayout;
    private LinearLayout ll_online;
    private TextView tv_username;
    private Button btn_login;


    private RecyclerView mRecyclerView;

    private List<Book> dataList = new ArrayList<>();
    private HomeAdapter homeAdapter;

    @Override
    public Class getTag(Class clazz) {
        return MainActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle(R.string.title_home).isTranslucent().setToolbarTopMargin();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_container);
        mNavigationView.setNavigationItemSelectedListener(this);
        headerLayout = mNavigationView.getHeaderView(0);

        ll_online = (LinearLayout) headerLayout.findViewById(R.id.ll_online);
        tv_username = (TextView) headerLayout.findViewById(R.id.tv_username);
        btn_login = (Button) headerLayout.findViewById(R.id.btn_login);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();

        homeAdapter = new HomeAdapter(dataList);
        mRecyclerView.setAdapter(homeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_space)));
        homeAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                BookInfoActivity.goActivity(MainActivity.this, dataList.get(position));
            }

            @Override
            public void onItemLongClick(View view, BaseViewHolder holder, int position) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.goActivity(MainActivity.this);
            }
        });
    }

    @Override
    public void initData() {
        dataList.clear();
        dataList.addAll(MainApplication.getInstance().getBookList());
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        refreshMenu();
    }

    private void refreshMenu() {
        if (MainApplication.getInstance().isOnline()) {
            ll_online.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);
            tv_username.setText(MainApplication.getInstance().getAdmin().getUsername());
            mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
        } else {
            ll_online.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
            mNavigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    initData();
                } else {
                    List<Book> searchList = MainApplication.getInstance().getBookByKeyword(query);
                    dataList.clear();
                    dataList.addAll(searchList);
                    homeAdapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    initData();
                } else {
                    List<Book> searchList = MainApplication.getInstance().getBookByKeyword(newText);
                    dataList.clear();
                    dataList.addAll(searchList);
                    homeAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (MainApplication.getInstance().isOnline()) {
            switch (id) {
                case R.id.nav_books:
                    BookListActivity.goActivity(MainActivity.this);
                    break;
                case R.id.nav_readers:
                    UserListActivity.goActivity(MainActivity.this);
                    break;
                case R.id.nav_info:
                    InformationActivity.goActivity(MainActivity.this);
                    break;
                case R.id.nav_logout:
                    MainApplication.getInstance().setAdmin(null);
                    refreshMenu();
                    break;
            }
        } else {
            LoginActivity.goActivity(MainActivity.this);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
