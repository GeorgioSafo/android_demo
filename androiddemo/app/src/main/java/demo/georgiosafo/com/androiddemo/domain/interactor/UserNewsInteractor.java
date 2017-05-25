package demo.georgiosafo.com.androiddemo.domain.interactor;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserNewsInteractor;
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
    public void getUserNewsList(Subscriber<List<UserNewsLocalData>> subscriber) {
        if (userNewsRepository.isCached()) {
            subscriber.onNext(userNewsRepository.getMemoryData(null));
        } else {
            userNewsRepository
                    .getLocalData(null)
                    .subscribe(subscriber);
            subscription = userNewsRepository
                    .getNetworkData(null)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }
}
