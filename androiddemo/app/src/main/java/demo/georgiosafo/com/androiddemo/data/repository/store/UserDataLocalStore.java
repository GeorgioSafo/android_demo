package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.database.IRealmService;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import rx.Observable;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserDataLocalStore implements IDataLocalStore<List<UserLocalData>> {


    private IRealmService realmService;

    public UserDataLocalStore(IRealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public Observable<List<UserLocalData>> getDataWithParams(HashMap<String, Object> map) {
        return realmService.getAllUserLocalData();
    }

    @Override
    public void saveData(List<UserLocalData> data) {
        realmService.updateUserLocalData(data);
    }
}

