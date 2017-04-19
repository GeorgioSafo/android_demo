package demo.georgiosafo.com.androiddemo.domain.interactor.interfaces;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IUserInteractor extends IBaseInteractor{
    void requestUserList(Subscriber<ArrayList<UserLocalData>> subscriber);
}
