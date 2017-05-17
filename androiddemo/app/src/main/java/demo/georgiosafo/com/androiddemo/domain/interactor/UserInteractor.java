package demo.georgiosafo.com.androiddemo.domain.interactor;

import java.io.IOException;
import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class UserInteractor implements IUserInteractor {

    private Subscription subscription = Subscriptions.empty();
    private UserRepository userRepository;


    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    @Override
    public void requestUserList(Subscriber<ArrayList<UserLocalData>> subscriber) {
        subscription = Observable.just(userRepository).map(repository -> {
            try {
                return repository.getData();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        })
                .subscribeOn(Schedulers.newThread())
                //// TODO: 19.04.17 Change to PostExecutionThread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
