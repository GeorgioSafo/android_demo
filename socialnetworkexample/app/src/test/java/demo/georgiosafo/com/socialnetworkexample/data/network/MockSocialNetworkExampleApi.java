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
package demo.georgiosafo.com.socialnetworkexample.data.network;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;
import rx.Observable;

/**
 * Created by gevorksafaryan on 23.05.17.
 */

public class MockSocialNetworkExampleApi implements SocialNetworkExampleApi {


    private static final String FAKE_ID = "dsh34-oer223";
    private static final String FAKE_FIRST_NAME = "Bob";
    private static final String FAKE_MIDDLE_NAME = "Sponge";
    private static final String FAKE_LAST_NAME = "SquarePants";
    private static final int FAKE_AGE = 40;
    private static final String FAKE_URL = "http://vignette3.wikia.nocookie.net/spongebob/images/6/6f/SpongeBob_%286%29.png";
    private static final String FAKE_EMAIL = "spongeBob@spongeMail.com";


    @Override
    public Observable<List<UserRemoteData>> getUsers() {
        return Observable.just(createListFakeUserRemoteData());
    }

    @Override
    public Observable<List<UserNewsRemoteData>> getNews() {
        return null;
    }

    private List<UserRemoteData> createListFakeUserRemoteData() {
        List<UserRemoteData> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserRemoteData remoteData = new UserRemoteData();
            remoteData.setServerId(String.valueOf(i));
            remoteData.setFirstName(FAKE_FIRST_NAME);
            remoteData.setMiddleName(FAKE_MIDDLE_NAME);
            remoteData.setLastName(FAKE_LAST_NAME);
            remoteData.setAge(FAKE_AGE);
            remoteData.setAvatarUrl(FAKE_URL);
            remoteData.setEmail(FAKE_EMAIL);
            list.add(remoteData);
        }
        return list;
    }

}
