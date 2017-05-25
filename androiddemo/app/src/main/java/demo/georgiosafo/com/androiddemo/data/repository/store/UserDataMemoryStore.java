package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;

/**
 * Created by gevorksafaryan on 23.05.17.
 */

public class UserDataMemoryStore implements IDataMemoryStore<List<UserLocalData>> {


    List<UserLocalData> data;

    @Override
    public List<UserLocalData> getDatawithParams(HashMap<String, Object> map) {
        return data;
    }

    @Override
    public void setData(List<UserLocalData> data) {
        this.data = data;
    }

    @Override
    public boolean isCached() {
        return data != null;
    }
}
