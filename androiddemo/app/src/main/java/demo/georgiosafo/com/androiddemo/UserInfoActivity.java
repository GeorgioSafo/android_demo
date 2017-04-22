package demo.georgiosafo.com.androiddemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.di.component.AndroidDemoAppComponent;
import demo.georgiosafo.com.androiddemo.di.component.DaggerUserInfoComponent;
import demo.georgiosafo.com.androiddemo.di.module.UserInfoModule;
import demo.georgiosafo.com.androiddemo.presentation.BaseActivity;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;

public class UserInfoActivity extends BaseActivity implements UserInfoView, AppBarLayout.OnOffsetChangedListener {

    public static final String USER = "user";
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private int mMaxScrollSize;

    @Inject
    IUserInfoPresenter userInfoPresenter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.history_list)
    RecyclerView historyList;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.backgroundProfile)
    SimpleDraweeView mBackgroundProfile;

    @Bind(R.id.avatar_imageView)
    SimpleDraweeView mAvatarProfile;

    @Bind(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.header_text)
    TextView mHeaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);
        ButterKnife.bind(this);

        initializeSimpleDraweeView(mBackgroundProfile);
        initializeSimpleDraweeView(mAvatarProfile);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras().containsKey(USER)) {
            UserLocalData localData = (UserLocalData) getIntent().getExtras().get(USER);
            userInfoPresenter.completeFields(localData);
        }


        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

        mAppBarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = mAppBarLayout.getTotalScrollRange();
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
    }

    @Override
    public void setBackground(String Url) {
        setUrl(mBackgroundProfile, Url);
    }

    private void setUrl(SimpleDraweeView view, String url) {
        ImageRequest requestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController contoller = Fresco.newDraweeControllerBuilder().setImageRequest(requestBuilder).build();
        view.setController(contoller);
    }

    private void initializeSimpleDraweeView(SimpleDraweeView simpleDraweeView) {
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.ic_profile, ScalingUtils.ScaleType.CENTER_INSIDE);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
    }

    @Override
    public void setHistory() {

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
}
