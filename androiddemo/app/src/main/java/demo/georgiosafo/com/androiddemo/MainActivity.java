package demo.georgiosafo.com.androiddemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.di.component.AndroidDemoAppComponent;
import demo.georgiosafo.com.androiddemo.di.component.DaggerMainComponent;
import demo.georgiosafo.com.androiddemo.di.module.MainModule;
import demo.georgiosafo.com.androiddemo.presentation.BaseActivity;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.adapters.UserAdapter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.MainView;

public class MainActivity extends BaseActivity implements MainView {


    @Inject
    IMainPresenter mainPresenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.user_recycler)
    RecyclerView userList;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        initRecylerView();
        mainPresenter.loadUsers();
    }

    private void initRecylerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        userList.setLayoutManager(mLayoutManager);
        userList.setItemAnimator(new DefaultItemAnimator());
        userAdapter = new UserAdapter(this, this);
        userList.setAdapter(userAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    protected void setupComponent(AndroidDemoAppComponent appComponent) {
        DaggerMainComponent.builder().androidDemoAppComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(userList, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUsers(ArrayList<UserLocalData> userLocalDatas) {
        userAdapter.setData(userLocalDatas);
    }


    /**
     * Start new Activity
     *
     * @param pair view to animation
     * @param user Object of User
     */
    @Override
    public void startTransition(@NonNull Pair<View, String> pair, UserLocalData user) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(UserInfoActivity.USER, user);
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
