package demo.georgiosafo.com.androiddemo.data.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataRemoteStore;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserRepository implements IDataRepository<ArrayList<UserLocalData>> {
    private final UserDataLocalStore localStore;
    private final UserDataRemoteStore remoteStore;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param localStore  {@link UserDataLocalStore}.
     * @param remoteStore {@link UserDataRemoteStore}.
     */
    public UserRepository(UserDataLocalStore localStore, UserDataRemoteStore remoteStore) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
    }


    /**
     * @return {@link UserLocalData}
     */
    @Override
    public ArrayList<UserLocalData> getData() throws IOException {
        if (localStore.isExpired()) {
            ArrayList<UserLocalData> data = remoteStore.getData();
            localStore.setData(data);
            return data;
        } else {
            return localStore.getData();
        }
    }

    /**
     * @param map - params for request
     * @return {@link UserLocalData}
     */
    @Override
    public ArrayList<UserLocalData> getDataWithParams(HashMap<String, Object> map) throws IOException {
        if (localStore.isExpired()) {
            ArrayList<UserLocalData> data = remoteStore.getDataWithParams(map);
            localStore.setData(data);
            return data;
        } else {
            return localStore.getData();
        }
    }
}
