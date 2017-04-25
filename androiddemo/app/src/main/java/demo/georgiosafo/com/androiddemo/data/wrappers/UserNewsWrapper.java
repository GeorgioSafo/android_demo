package demo.georgiosafo.com.androiddemo.data.wrappers;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsWrapper {
    /**
     * Wrap server data to local
     *
     * @param userRemoteDataArrayList collection response
     * @return collection of user news
     */
    public ArrayList<UserNewsLocalData> wrapUserRemoteData(ArrayList<UserNewsRemoteData> userRemoteDataArrayList) {
        ArrayList<UserNewsLocalData> userLocalDataList = new ArrayList<>();
        for (UserNewsRemoteData dataItem : userRemoteDataArrayList) {
            UserNewsLocalData localData = new UserNewsLocalData();
            localData.setLocalId(dataItem.getServerId());
            localData.setTitle(dataItem.getTitle());
            localData.setDate(dataItem.getDate());
            localData.setPost(dataItem.getPost());
            userLocalDataList.add(localData);
        }
        return userLocalDataList;
    }
}
