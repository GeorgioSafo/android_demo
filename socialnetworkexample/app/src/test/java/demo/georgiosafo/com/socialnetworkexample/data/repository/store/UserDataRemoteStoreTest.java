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
package demo.georgiosafo.com.socialnetworkexample.data.repository.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.network.MockSocialNetworkExampleApi;
import demo.georgiosafo.com.socialnetworkexample.data.network.SocialNetworkExampleApi;
import rx.Subscription;
import rx.observers.TestSubscriber;

/**
 * Created by gevorksafaryan on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDataRemoteStoreTest {

    private UserDataRemoteStore userDataRemoteStore;

    private SocialNetworkExampleApi mockSocialNetworkExampleApi;

    @Before
    public void setUp() {
        mockSocialNetworkExampleApi = new MockSocialNetworkExampleApi();
        userDataRemoteStore = new UserDataRemoteStore(mockSocialNetworkExampleApi);
    }

    @Test
    public void testGetUserEntityListFromApi() {
        //arrange
        TestSubscriber<List<UserRemoteData>> testSubscriber = new TestSubscriber<List<UserRemoteData>>();

        //act
        Subscription subscription = userDataRemoteStore.getDataWithParams(null).subscribe(testSubscriber);

        //assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertCompleted();
    }

    @After
    public void cleanUp() {
        mockSocialNetworkExampleApi = null;
        userDataRemoteStore = null;
    }
}
