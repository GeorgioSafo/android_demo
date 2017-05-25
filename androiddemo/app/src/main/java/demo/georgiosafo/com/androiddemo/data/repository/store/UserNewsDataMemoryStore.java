package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;

/**
 * Created by gevorksafaryan on 23.05.17.
 */

public class UserNewsDataMemoryStore implements IDataMemoryStore<List<UserNewsLocalData>> {


    List<UserNewsLocalData> data;

    @Override
    public List<UserNewsLocalData> getDatawithParams(HashMap<String, Object> map) {
        return data;
    }

    @Override
    public void setData(List<UserNewsLocalData> data) {
        this.data = data;
    }

    @Override
    public boolean isCached() {
        return data != null;
    }
}
