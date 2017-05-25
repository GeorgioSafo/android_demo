package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import rx.Observable;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class UserDataRemoteStore implements IDataRemoteStore<List<UserRemoteData>> {


    private final AndroidDemoApi api;

    public UserDataRemoteStore(AndroidDemoApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<UserRemoteData>> getDataWithParams(HashMap<String, Object> map) {
        return api.getUsers();
    }
}
