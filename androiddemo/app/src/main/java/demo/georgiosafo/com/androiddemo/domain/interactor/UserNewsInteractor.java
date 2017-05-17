package demo.georgiosafo.com.androiddemo.domain.interactor;

import java.io.IOException;
import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserNewsInteractor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsInteractor implements IUserNewsInteractor {

    private Subscription subscription = Subscriptions.empty();
    private UserNewsRepository userNewsRepository;

    public UserNewsInteractor(UserNewsRepository userNewsRepository) {
        this.userNewsRepository = userNewsRepository;
    }

    @Override
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }


    @Override
    public void requestUserNewsList(Subscriber<ArrayList<UserNewsLocalData>> subscriber) {
        subscription = Observable.just(userNewsRepository).map(repository -> {
            try {
                return repository.getData();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
