package demo.georgiosafo.com.androiddemo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
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
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.di.component.AndroidDemoAppComponent;
import demo.georgiosafo.com.androiddemo.di.component.DaggerUserInfoComponent;
import demo.georgiosafo.com.androiddemo.di.module.UserInfoModule;
import demo.georgiosafo.com.androiddemo.presentation.BaseActivity;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.adapters.UserNewsAdapter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;

public class UserInfoActivity extends BaseActivity implements UserInfoView, AppBarLayout.OnOffsetChangedListener {

    public static final String USER_ID = "user_id";
    public static final String USER_TITLE = "user_title";
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    public static final String USER_AVATAR_URL = "user_avatar_url";
    private boolean mIsAvatarShown = true;

    private int mMaxScrollSize;

    @Inject
    IUserInfoPresenter userInfoPresenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.history_list)
    RecyclerView historyList;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.backgroundProfile)
    ImageView mBackgroundProfile;

    @Bind(R.id.transparent_view)
    View mTransparentView;

    @Bind(R.id.avatar_imageView)
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
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.excludeTarget(mAppBarLayout, true);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            slide.excludeTarget(mAvatarProfile, true);
            getWindow().setEnterTransition(slide);
            getWindow().setAllowEnterTransitionOverlap(true);
            supportPostponeEnterTransition();
        }


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecylerView();

        parseIntent(getIntent());

        animateEnter();

        mAppBarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = mAppBarLayout.getTotalScrollRange();
    }

    private void animateEnter() {
        mTransparentView.setAlpha(0.0f);
        mTransparentView.animate().alpha(1.0f).setDuration(1000);
        mAvatarProfile.setScaleX(0);
        mAvatarProfile.setScaleY(0);
        mAvatarProfile.animate()
                .scaleY(1).scaleX(1).setDuration(500);
    }


    private void parseIntent(Intent intent) {
        if (intent.getExtras().containsKey(USER_ID)) {
            userInfoPresenter.loadUser(getIntent().getExtras().getString(USER_ID));
        }
        if (intent.getExtras().containsKey(USER_TITLE)) {
            setTitle(getIntent().getExtras().getString(USER_TITLE));
        }
        if (intent.getExtras().containsKey(USER_AVATAR_URL)) {
            setAvatar(getIntent().getExtras().getString(USER_AVATAR_URL));
            setBackground(getIntent().getExtras().getString(USER_AVATAR_URL));
        }
    }

    @Override
    protected void setupComponent(AndroidDemoAppComponent appComponent) {
        DaggerUserInfoComponent.builder().androidDemoAppComponent(appComponent).userInfoModule(new UserInfoModule(this)).build().inject(this);
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
                        if (view.getId() == mBackgroundProfile.getId()) {
                            supportStartPostponedEnterTransition();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (view.getId() == mBackgroundProfile.getId()) {
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
        mHeaderText.setText(s);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0) {
            mMaxScrollSize = appBarLayout.getTotalScrollRange();
        }
        int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            mAvatarProfile.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;
            mAvatarProfile.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        userInfoPresenter.onPause();
    }
}
