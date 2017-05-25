package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

import java.util.HashMap;

/**
 * Created by gevorksafaryan on 22.05.17.
 */

public interface IDataMemoryStore<T> {

    T getDatawithParams(HashMap<String, Object> map);

    void setData(T data);

    boolean isCached();
}
