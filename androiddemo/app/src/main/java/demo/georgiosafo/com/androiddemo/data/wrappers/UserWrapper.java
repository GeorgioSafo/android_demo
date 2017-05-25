package demo.georgiosafo.com.androiddemo.data.wrappers;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserWrapper {
    /**
     * Wrap server data to local
     *
     * @param userRemoteDataArrayList collection response
     * @return
     */
    public List<UserLocalData> wrapUserRemoteData(List<UserRemoteData> userRemoteDataArrayList) {
        ArrayList<UserLocalData> userLocalDataList = new ArrayList<>(10);
        for (UserRemoteData dataItem : userRemoteDataArrayList) {
            final UserLocalData userLocalData = wrapUserRemoteData(dataItem);
            if (userLocalData != null) {
                userLocalDataList.add(userLocalData);
            }
        }
        return userLocalDataList;
    }

    /**
     * Wrap server data to local
     *
     * @param userRemoteData instanse of {@link UserRemoteData}
     * @return {@link UserLocalData} if valid {@link UserRemoteData} otherwise null
     */
    public UserLocalData wrapUserRemoteData(UserRemoteData userRemoteData) {
        UserLocalData localData = null;
        if (userRemoteData != null) {
            localData = new UserLocalData();
            localData.setLocalId(userRemoteData.getServerId());
            localData.setFirstName(userRemoteData.getFirstName());
            localData.setMiddleName(userRemoteData.getMiddleName());
            localData.setLastName(userRemoteData.getLastName());
            localData.setAge(userRemoteData.getAge());
            localData.setAvatarUrl(userRemoteData.getAvatarUrl());
            localData.setEmail(userRemoteData.getEmail());
        }
        return localData;
    }
}
