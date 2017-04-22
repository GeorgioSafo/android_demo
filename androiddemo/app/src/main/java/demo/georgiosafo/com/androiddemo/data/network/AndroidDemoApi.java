package demo.georgiosafo.com.androiddemo.data.network;



import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public interface AndroidDemoApi {

    @GET("a828a5cca4460512767bf80a321d465c/raw/82a809bf4559e063d3ef5a8a886e821612916d23/gistfile1.txt")
    Call<ArrayList<UserRemoteData>> getUsers();
}
