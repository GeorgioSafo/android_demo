package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IDataLocalStore<T> {

    T getData();

    void setData(T data);

    boolean isExpired();

}
