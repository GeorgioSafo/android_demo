package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserDataLocalStore implements IDataLocalStore<ArrayList<UserLocalData>> {

    ArrayList<UserLocalData> data = null;

    @Override
    public ArrayList<UserLocalData> getData() {
        return data;
    }

    @Override
    public void setData(ArrayList<UserLocalData> data) {
        this.data = data;
    }

    @Override
    public boolean isExpired() {
        return data == null || data.size() == 0;
    }
}

