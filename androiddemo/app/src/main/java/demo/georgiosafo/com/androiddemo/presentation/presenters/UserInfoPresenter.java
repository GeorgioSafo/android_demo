package demo.georgiosafo.com.androiddemo.presentation.presenters;

import java.util.ArrayList;
import java.util.Locale;

import demo.georgiosafo.com.androiddemo.AndroidDemoApp;
import demo.georgiosafo.com.androiddemo.R;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserNewsInteractor;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public class UserInfoPresenter implements IUserInfoPresenter {
    private UserInfoView view;
    private IUserNewsInteractor interactor;

    public UserInfoPresenter(UserInfoView view, IUserNewsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {
        interactor.unsubscribe();
        view = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void loadUser(String userId) {
        interactor.requestUserNewsList(subscriber);
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

    private Subscriber<ArrayList<UserNewsLocalData>> subscriber = new Subscriber<ArrayList<UserNewsLocalData>>() {
        @Override
        public void onStart() {
            view.showProgress();
        }


        @Override
        public void onCompleted() {
            view.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            view.hideProgress();
            view.showError(e.getMessage());
        }

        @Override
        public void onNext(ArrayList<UserNewsLocalData> userLocalDatas) {
            view.hideProgress();
            view.setHistory(userLocalDatas);
        }
    };
}
