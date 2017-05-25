package demo.georgiosafo.com.androiddemo.domain.interactor.interfaces;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IUserInteractor extends IBaseInteractor {
    void getUserList(Subscriber<List<UserLocalData>> subscriber);
}
