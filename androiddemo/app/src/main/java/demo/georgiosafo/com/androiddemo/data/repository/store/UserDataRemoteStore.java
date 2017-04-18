package demo.georgiosafo.com.androiddemo.data.repository.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserDataRemoteStore implements IDataRemoteStore<ArrayList<UserLocalData>> {

    //todo add retrofit
    @Override
    public ArrayList<UserLocalData> getData() throws IOException {
        return null;
    }

    @Override
    public ArrayList<UserLocalData> getDataWithParams(HashMap<String, Object> map) throws IOException {
        return null;
    }
}
