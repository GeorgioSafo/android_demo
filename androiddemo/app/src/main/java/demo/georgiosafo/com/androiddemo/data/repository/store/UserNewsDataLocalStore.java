package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsDataLocalStore implements IDataLocalStore<ArrayList<UserNewsLocalData>> {

    ArrayList<UserNewsLocalData> data = null;

    @Override
    public ArrayList<UserNewsLocalData> getData() {
        return data;
    }

    @Override
    public void setData(ArrayList<UserNewsLocalData> data) {
        this.data = data;
    }

    @Override
    public boolean isExpired() {
        return data == null || data.size() == 0;
    }
}

