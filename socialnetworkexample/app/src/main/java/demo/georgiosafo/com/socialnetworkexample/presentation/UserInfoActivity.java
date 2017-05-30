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
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.georgiosafo.com.socialnetworkexample.R;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.di.component.DaggerUserInfoComponent;
import demo.georgiosafo.com.socialnetworkexample.di.component.SocialNetworkExampleAppComponent;
import demo.georgiosafo.com.socialnetworkexample.di.module.UserInfoModule;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.adapters.UserNewsAdapter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.UserInfoView;

public class UserInfoActivity extends BaseActivity implements UserInfoView{

    public static final String USER_ID = "user_id";
    public static final String USER_TITLE = "user_title";
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    public static final String USER_AVATAR_URL = "user_avatar_url";
    public static final String USER_HEADER_TEXT = "user_header_text";
    private boolean mIsAvatarShown = true;

    private int mMaxScrollSize;

    @Inject
    IUserInfoPresenter userInfoPresenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @Bind(R.id.history_list)
    RecyclerView historyList;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.backgroundProfile)
    ImageView mBackgroundProfile;

    @Bind(R.id.transparent_view)
    View mTransparentView;

    @Bind(R.id.avatar_imageView_detail)
    ImageView mAvatarProfile;

    @Bind(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.header_text)
    TextView mHeaderText;

    private UserNewsAdapter userNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);
        ButterKnife.bind(this);
        collapsingToolbarLayout.getLayoutParams().height = Resources.getSystem().getDisplayMetrics().widthPixels;
        // Postpone the transition until the detail image thumbnail is loaded
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Slide slider = new Slide();
            slider.excludeTarget(mAppBarLayout, true);
            slider.excludeTarget(android.R.id.statusBarBackground, true);
            slider.excludeTarget(android.R.id.navigationBarBackground, true);
            slider.excludeTarget(mAvatarProfile, true);
            slider.excludeTarget(mTransparentView, true);
            slider.excludeTarget(mBackgroundProfile, true);

            Fade fade = new Fade();
            fade.excludeTarget(nestedScrollView, true);
            fade.excludeTarget(floatingActionButton, true);
            fade.excludeTarget(mAvatarProfile, true);
            fade.excludeTarget(mHeaderText, true);

            TransitionSet set = new TransitionSet();
            set.addTransition(slider);
            set.addTransition(fade);
            getWindow().setEnterTransition(set);
            getWindow().setSharedElementsUseOverlay(true);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            supportPostponeEnterTransition();
        }


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecylerView();

        parseIntent(getIntent());
    }


    private void parseIntent(Intent intent) {
        if (intent.getExtras().containsKey(USER_TITLE)) {
            setTitle(intent.getExtras().getString(USER_TITLE));
        }
        if (intent.getExtras().containsKey(USER_AVATAR_URL)) {
            setAvatar(intent.getExtras().getString(USER_AVATAR_URL));
            setBackground(intent.getExtras().getString(USER_AVATAR_URL));
        }
        if (intent.getExtras().containsKey(USER_HEADER_TEXT)) {
            setHeaderText(intent.getExtras().getString(USER_HEADER_TEXT));
        }
        if (intent.getExtras().containsKey(USER_ID)) {
            userInfoPresenter.loadUser(getIntent().getExtras().getString(USER_ID));
        }
    }

    @Override
    protected void setupComponent(SocialNetworkExampleAppComponent appComponent) {
        DaggerUserInfoComponent.builder().socialNetworkExampleAppComponent(appComponent).userInfoModule(new UserInfoModule(this)).build().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecylerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        historyList.setLayoutManager(mLayoutManager);
        historyList.setItemAnimator(new DefaultItemAnimator());
        userNewsAdapter = new UserNewsAdapter(this, this);
        historyList.setAdapter(userNewsAdapter);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setSubTitle(String subTitle) {
        getSupportActionBar().setSubtitle(subTitle);
    }

    @Override
    public void setAvatar(String Url) {
        setUrl(mAvatarProfile, Url);
        if (userNewsAdapter != null) {
            userNewsAdapter.setImageUri(Url);
        }
    }

    @Override
    public void setBackground(String Url) {
        setUrl(mBackgroundProfile, Url);
    }

    private void setUrl(ImageView view, String url) {
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (view.getId() == mAvatarProfile.getId()) {
                            supportStartPostponedEnterTransition();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (view.getId() == mAvatarProfile.getId()) {
                            supportStartPostponedEnterTransition();
                        }
                        return false;
                    }
                })
                .into(view);
    }

    @Override
    public void setHistory(List<UserNewsLocalData> userLocalDatas) {
        userNewsAdapter.setData(userLocalDatas);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public void setHeaderText(String s) {
        mHeaderText.setText(String.format("\"%s\"", s));
    }



    @Override
    protected void onPause() {
        super.onPause();
        userInfoPresenter.onPause();
    }
}
