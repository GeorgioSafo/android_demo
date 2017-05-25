package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IDataLocalStore<T> {

    Observable<T> getDataWithParams(HashMap<String,Object> map);

    void saveData(T data);
}
