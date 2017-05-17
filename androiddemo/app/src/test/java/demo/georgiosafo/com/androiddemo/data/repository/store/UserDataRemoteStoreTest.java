package demo.georgiosafo.com.androiddemo.data.repository.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by gevorksafaryan on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDataRemoteStoreTest {

    private UserDataRemoteStore userDataRemoteStore;

    @Mock
    private AndroidDemoApi mockAndroidDemoApi;

    @Before
    public void setUp() {
        userDataRemoteStore = new UserDataRemoteStore(mockAndroidDemoApi);
    }

    @Test
    public void testGetUserEntityListFromApi() {

        //act
        try {
            userDataRemoteStore.getData();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(NullPointerException.class)));
        }

        //assert
        verify(mockAndroidDemoApi).getUsers();
    }
}
