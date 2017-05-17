package demo.georgiosafo.com.androiddemo.domain.interactor.interfaces;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public interface IUserNewsInteractor extends IBaseInteractor {
    void requestUserNewsList(Subscriber<ArrayList<UserNewsLocalData>> subscriber);
}
