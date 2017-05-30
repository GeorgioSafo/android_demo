/**
 * Copyright (c) 2017 Gevork Safaryan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.georgiosafo.com.socialnetworkexample.presentation;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.georgiosafo.com.socialnetworkexample.R;
import demo.georgiosafo.com.socialnetworkexample.SocialNetworkExampleApp;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.di.component.DaggerMainComponent;
import demo.georgiosafo.com.socialnetworkexample.di.component.SocialNetworkExampleAppComponent;
import demo.georgiosafo.com.socialnetworkexample.di.module.MainModule;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.adapters.UserAdapter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.MainView;

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

    @Bind(R.id.error_layout)
    View errorView;

    @Bind(R.id.error_text)
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        initRecylerView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementsUseOverlay(false);
        }
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, R.color.icons), PorterDuff.Mode.SRC_IN);
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
    protected void setupComponent(SocialNetworkExampleAppComponent appComponent) {
        DaggerMainComponent.builder().socialNetworkExampleAppComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {
        if (userAdapter != null && userAdapter.getItemCount() > 0) {
            Snackbar.make(userList, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            errorView.setVisibility(View.VISIBLE);
            errorText.setText(errorMessage);
        }
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUsers(List<UserLocalData> userLocalDatas) {
        userAdapter.setData(userLocalDatas);
    }


    /**
     * Start new Activity
     *
     * @param pair view to animation
     * @param user Object of User
     */
    @Override
    public void startTransition(@NonNull Pair<View, String>[] pair, UserLocalData user) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(UserInfoActivity.USER_ID, user.getLocalId());
        bundle.putString(UserInfoActivity.USER_TITLE, String.format(Locale.getDefault(), SocialNetworkExampleApp.getSocialNetworkExampleApp().getResources().getString(R.string.name_format), user.getFirstName(), user.getLastName()));
        bundle.putString(UserInfoActivity.USER_AVATAR_URL, user.getAvatarUrl());
        bundle.putString(UserInfoActivity.USER_HEADER_TEXT, user.getUserNews().getPost());
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair);
        ActivityCompat.startActivity(this, intent, options.toBundle());

    }
}
