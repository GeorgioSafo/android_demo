package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.wrappers.UserWrapper;
import rx.exceptions.OnErrorNotImplementedException;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class UserDataRemoteStore implements IDataRemoteStore<ArrayList<UserLocalData>> {


    private final AndroidDemoApi api;

    public UserDataRemoteStore(AndroidDemoApi api) {
        this.api = api;
    }

    @Override
    public ArrayList<UserLocalData> getData() throws IOException {
        ArrayList<UserRemoteData> remoteDataArrayList = api.getUsers().execute().body();
        return new UserWrapper().wrapUserRemoteData(remoteDataArrayList);
    }

    @Override
    public ArrayList<UserLocalData> getDataWithParams(HashMap<String, Object> map) throws IOException {
        //add params
        throw new OnErrorNotImplementedException(new Throwable());
    }
}
