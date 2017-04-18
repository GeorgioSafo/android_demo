package demo.georgiosafo.com.androiddemo.data.repository.interfaces;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public interface IDataRepository<T> {

    T getData() throws IOException;

    T getDataWithParams(HashMap<String, Object> map) throws IOException;

}
