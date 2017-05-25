package demo.georgiosafo.com.androiddemo.data.repository.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.network.MockAndroidDemoApi;
import rx.Subscription;
import rx.observers.TestSubscriber;

/**
 * Created by gevorksafaryan on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDataRemoteStoreTest {

    private UserDataRemoteStore userDataRemoteStore;

    private AndroidDemoApi mockAndroidDemoApi;

    @Before
    public void setUp() {
        mockAndroidDemoApi = new MockAndroidDemoApi();
        userDataRemoteStore = new UserDataRemoteStore(mockAndroidDemoApi);
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
        mockAndroidDemoApi = null;
        userDataRemoteStore = null;
    }
}
