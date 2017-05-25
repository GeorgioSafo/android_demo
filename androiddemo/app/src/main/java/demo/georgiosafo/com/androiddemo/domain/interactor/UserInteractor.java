package demo.georgiosafo.com.androiddemo.domain.interactor;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.util.SubscriptionList;
import rx.schedulers.Schedulers;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class UserInteractor implements IUserInteractor {

    private SubscriptionList subscription = new SubscriptionList();
    private UserRepository userRepository;


    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    @Override
    public void getUserList(Subscriber<List<UserLocalData>> subscriber) {
        if (userRepository.isCached()) {
            subscriber.onNext(userRepository.getMemoryData(null));
        } else {
            subscription.add(userRepository
                    .getLocalData(null)
                    .subscribe(subscriber));
            subscription.add(userRepository
                    .getNetworkData(null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
        }
    }
}
