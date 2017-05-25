package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import rx.Observable;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsDataRemoteStore implements IDataRemoteStore<List<UserNewsRemoteData>> {


    private final AndroidDemoApi api;

    public UserNewsDataRemoteStore(AndroidDemoApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<UserNewsRemoteData>> getDataWithParams(HashMap<String, Object> map) {
        return api.getNews();
    }
}
