package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IDataRepository<T> {

    Observable<T> getNetworkData(HashMap<String, Object> map);

    Observable<T> getLocalData(HashMap<String, Object> map);

    T getMemoryData(HashMap<String, Object> map);

    boolean isCached();
}
