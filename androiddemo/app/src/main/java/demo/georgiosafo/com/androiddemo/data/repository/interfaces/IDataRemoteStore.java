package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IDataRemoteStore<T> {
    Observable<T> getDataWithParams(HashMap<String, Object> map);
}
