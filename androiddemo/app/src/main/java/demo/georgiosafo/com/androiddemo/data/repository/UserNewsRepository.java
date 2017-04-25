package demo.georgiosafo.com.androiddemo.data.repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataRemoteStore;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsRepository implements IDataRepository<ArrayList<UserNewsLocalData>> {
    private final IDataLocalStore<ArrayList<UserNewsLocalData>> localStore;
    private final IDataRemoteStore<ArrayList<UserNewsLocalData>> remoteStore;

    /**
     * Constructs a {@link UserNewsRepository}.
     *
     * @param localStore  {@link UserNewsDataLocalStore}.
     * @param remoteStore {@link UserNewsDataRemoteStore}.
     */
    public UserNewsRepository(IDataLocalStore<ArrayList<UserNewsLocalData>> localStore, IDataRemoteStore<ArrayList<UserNewsLocalData>> remoteStore) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
    }


    /**
     * @return {@link UserNewsLocalData}
     */
    @Override
    public ArrayList<UserNewsLocalData> getData() throws IOException {
        if (localStore.isExpired()) {
            ArrayList<UserNewsLocalData> data = remoteStore.getData();
            localStore.setData(data);
            return sortData(data);
        } else {
            return sortData(localStore.getData());
        }
    }


    public ArrayList<UserNewsLocalData> sortData(ArrayList<UserNewsLocalData> data) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss", Locale.getDefault());
        Collections.sort(data, (o1, o2) -> {
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = format.parse(o1.getDate());
                date2 = format.parse(o2.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
            return date1.compareTo(date2);
        });
        return data;
    }

    /**
     * @param map - params for request
     * @return {@link UserNewsLocalData}
     */
    @Override
    public ArrayList<UserNewsLocalData> getDataWithParams(HashMap<String, Object> map) throws
            IOException {
        if (localStore.isExpired()) {
            ArrayList<UserNewsLocalData> data = remoteStore.getDataWithParams(map);
            localStore.setData(data);
            return data;
        } else {
            return localStore.getData();
        }
    }
}