package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.wrappers.UserNewsWrapper;
import rx.exceptions.OnErrorNotImplementedException;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsDataRemoteStore implements IDataRemoteStore<ArrayList<UserNewsLocalData>> {


    private final AndroidDemoApi api;

    public UserNewsDataRemoteStore(AndroidDemoApi api) {
        this.api = api;
    }

    @Override
    public ArrayList<UserNewsLocalData> getData() throws IOException {
        ArrayList<UserNewsRemoteData> remoteDataArrayList = api.getNews().execute().body();
        return new UserNewsWrapper().wrapUserRemoteData(remoteDataArrayList);
    }

    @Override
    public ArrayList<UserNewsLocalData> getDataWithParams(HashMap<String, Object> map) throws IOException {
        //add params
        throw new OnErrorNotImplementedException(new Throwable());
    }
}
