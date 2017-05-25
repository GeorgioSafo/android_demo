package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.database.IRealmService;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import rx.Observable;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsDataLocalStore implements IDataLocalStore<List<UserNewsLocalData>> {

    private final IRealmService realmService;

    public UserNewsDataLocalStore(IRealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public Observable<List<UserNewsLocalData>> getDataWithParams(HashMap<String, Object> map) {
        return realmService.getNews();
    }

    @Override
    public void saveData(List<UserNewsLocalData> data) {

    }
}

