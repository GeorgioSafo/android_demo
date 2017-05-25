package demo.georgiosafo.com.androiddemo.data.database;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import rx.Observable;

/**
 * Created by gevorksafaryan on 22.05.17.
 */

public interface IRealmService {

    Observable<List<UserLocalData>> getAllUserLocalData();

    void updateUserLocalData(List<UserLocalData> data);

    void closeRealm();

    Observable<List<UserNewsLocalData>> getNews();

    void updateUserNewsData(List<UserNewsLocalData> data);
}
