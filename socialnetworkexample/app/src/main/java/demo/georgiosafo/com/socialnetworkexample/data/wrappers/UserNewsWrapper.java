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

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;

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
            Long longDate = DateWrapper.stringToLong(userNewsRemoteData.getDate());
            if (longDate == null) {
                return null;
            } else {
                localData.setDate(longDate);
            }
            localData.setLocalId(userNewsRemoteData.getServerId());
            localData.setTitle(userNewsRemoteData.getTitle());
            localData.setPost(userNewsRemoteData.getPost());
        }
        return localData;
    }
}
