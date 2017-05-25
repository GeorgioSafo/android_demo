package demo.georgiosafo.com.androiddemo.presentation.presenters;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.MainView;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class MainPresenter implements IMainPresenter {

    private MainView mainView;
    private IUserInteractor interactor;

    public MainPresenter(MainView mainView, IUserInteractor interactor) {
        this.mainView = mainView;
        this.interactor = interactor;
    }

    @Override
    public void loadUsers() {
        interactor.getUserList(subscriber);
    }

    @Override
    public void reloadData() {

    }

    @Override
    public void onDestroy() {
        interactor.unsubscribe();
        mainView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private Subscriber<List<UserLocalData>> subscriber = new Subscriber<List<UserLocalData>>() {
        @Override
        public void onStart() {
            mainView.showProgress();
        }


        @Override
        public void onCompleted() {
            mainView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            mainView.hideProgress();
            mainView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<UserLocalData> userLocalDatas) {
            mainView.hideProgress();
            mainView.showUsers(userLocalDatas);
        }
    };
}
