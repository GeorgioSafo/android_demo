/**
 * Copyright (c) 2017 Gevork Safaryan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.georgiosafo.com.socialnetworkexample.data.wrappers;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;

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
            localData.setUserNews(new UserNewsWrapper().wrapUserRemoteData(userRemoteData.getUserNews()));
        }
        return localData;
    }
}
