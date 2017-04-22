package demo.georgiosafo.com.androiddemo.presentation.presenters;

import java.util.Locale;

import demo.georgiosafo.com.androiddemo.AndroidDemoApp;
import demo.georgiosafo.com.androiddemo.R;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public class UserInfoPresenter implements IUserInfoPresenter {
    private UserInfoView view;
    private IUserInteractor interactor;

    public UserInfoPresenter(UserInfoView view, IUserInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void loadUser(String userId) {

    }

    @Override
    public void completeFields(UserLocalData localData) {
        view.setBackground(localData.getAvatarUrl());
        view.setAvatar(localData.getAvatarUrl());
        view.setTitle(String.format(Locale.getDefault(), AndroidDemoApp.getAndroidDemoApp().getResources().getString(R.string.name_format), localData.getFirstName(), localData.getLastName()));
        view.setSubTitle(String.valueOf(localData.getAge()));

        StringBuilder sb = new StringBuilder()
                .append("Age: ")
                .append(String.valueOf(localData.getAge()))
                .append(System.getProperty("line.separator"))
                .append("E-mail: ").append(localData.getEmail());
        view.setHeaderText(sb.toString());
    }
}
