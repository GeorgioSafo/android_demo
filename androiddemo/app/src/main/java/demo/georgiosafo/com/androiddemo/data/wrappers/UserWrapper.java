package demo.georgiosafo.com.androiddemo.data.wrappers;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserWrapper {
    public ArrayList<UserLocalData> wrapUserRemoteData(ArrayList<UserRemoteData> userRemoteDataArrayList) {
        ArrayList<UserLocalData> userLocalDataList = new ArrayList<>();
        for (UserRemoteData dataItem : userRemoteDataArrayList) {
            UserLocalData localData = new UserLocalData();
            localData.setLocalId(dataItem.getServerId());
            localData.setFirstName(dataItem.getFirstName());
            localData.setMiddleName(dataItem.getMiddleName());
            localData.setLastName(dataItem.getLastName());
            localData.setAge(dataItem.getAge());
            localData.setAvatarUrl(dataItem.getAvatarUrl());
            localData.setEmail(dataItem.getEmail());
            userLocalDataList.add(localData);
        }
        return userLocalDataList;
    }
}
