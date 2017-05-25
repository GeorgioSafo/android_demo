package demo.georgiosafo.com.androiddemo.data.wrappers;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsWrapper {
    /**
     * Wrap collection of {@link UserNewsRemoteData} to collection of {@link UserNewsLocalData}
     *
     * @param userRemoteDataList collection to be wrapped
     * @return collection of user news
     */
    public List<UserNewsLocalData> wrapUserRemoteData(List<UserNewsRemoteData> userRemoteDataList) {
        final List<UserNewsLocalData> userLocalDataList = new ArrayList<>();
        for (UserNewsRemoteData dataItem : userRemoteDataList) {
            final UserNewsLocalData userNewsLocalData = wrapUserRemoteData(dataItem);
            if (userNewsLocalData != null) {
                userLocalDataList.add(userNewsLocalData);
            }
        }
        return userLocalDataList;
    }

    /**
     * Wrap server data to local
     *
     * @param userNewsRemoteData instanse of {@link UserNewsRemoteData}
     * @return {@link UserNewsLocalData} if valid {@link UserNewsRemoteData} otherwise null
     */
    public UserNewsLocalData wrapUserRemoteData(UserNewsRemoteData userNewsRemoteData) {
        UserNewsLocalData localData = null;
        if (userNewsRemoteData != null) {
            localData = new UserNewsLocalData();
            localData.setLocalId(userNewsRemoteData.getServerId());
            localData.setTitle(userNewsRemoteData.getTitle());
            localData.setDate(userNewsRemoteData.getDate());
            localData.setPost(userNewsRemoteData.getPost());
        }
        return localData;
    }
}
