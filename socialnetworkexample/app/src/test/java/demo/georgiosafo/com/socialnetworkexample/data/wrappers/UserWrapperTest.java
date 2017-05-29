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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by gevorksafaryan on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserWrapperTest {
    //fakefiels
    private static final String FAKE_ID = "dsh34-oer223";
    private static final String FAKE_FIRST_NAME = "Bob";
    private static final String FAKE_MIDDLE_NAME = "Sponge";
    private static final String FAKE_LAST_NAME = "SquarePants";
    private static final int FAKE_AGE = 40;
    private static final String FAKE_URL = "http://vignette3.wikia.nocookie.net/spongebob/images/6/6f/SpongeBob_%286%29.png";
    private static final String FAKE_EMAIL = "spongeBob@spongeMail.com";

    UserWrapper userWrapper;

    @Before
    public void setUp() {
        userWrapper = new UserWrapper();
    }

    @Test
    public void testWrapUserDataRemote() {
        //arrange
        UserRemoteData userRemoteData = createFakeUserRemoteData();

        //act
        UserLocalData userLocalData = userWrapper.wrapUserRemoteData(userRemoteData);

        //assert
        assertThat(userLocalData, is(instanceOf(UserLocalData.class)));
        assertThat(userLocalData.getLocalId(), is(FAKE_ID));
        assertThat(userLocalData.getFirstName(), is(FAKE_FIRST_NAME));
        assertThat(userLocalData.getMiddleName(), is(FAKE_MIDDLE_NAME));
        assertThat(userLocalData.getLastName(), is(FAKE_LAST_NAME));
        assertThat(userLocalData.getAge(), is(FAKE_AGE));
        assertThat(userLocalData.getAvatarUrl(), is(FAKE_URL));
        assertThat(userLocalData.getEmail(), is(FAKE_EMAIL));
    }


    @Test
    public void testTransformUserEntityCollection() {
        //arrange
        UserRemoteData mockUserRemoteDataFirst = mock(UserRemoteData.class);
        UserRemoteData mockUserRemoteDataSecond = mock(UserRemoteData.class);

        ArrayList<UserRemoteData> userRemoteDataArrayList = new ArrayList<>(10);
        userRemoteDataArrayList.add(mockUserRemoteDataFirst);
        userRemoteDataArrayList.add(mockUserRemoteDataSecond);

        //act
        List<UserLocalData> userNewsLocalDataList = userWrapper.wrapUserRemoteData(userRemoteDataArrayList);

        //assert
        assertThat(userNewsLocalDataList.toArray()[0], is(instanceOf(UserLocalData.class)));
        assertThat(userNewsLocalDataList.toArray()[1], is(instanceOf(UserLocalData.class)));
        assertThat(userNewsLocalDataList.size(), is(2));
    }

    private UserRemoteData createFakeUserRemoteData() {
        UserRemoteData remoteData = new UserRemoteData();
        remoteData.setServerId(FAKE_ID);
        remoteData.setFirstName(FAKE_FIRST_NAME);
        remoteData.setMiddleName(FAKE_MIDDLE_NAME);
        remoteData.setLastName(FAKE_LAST_NAME);
        remoteData.setAge(FAKE_AGE);
        remoteData.setAvatarUrl(FAKE_URL);
        remoteData.setEmail(FAKE_EMAIL);
        return remoteData;
    }

    @After
    public void cleanUp() {
        userWrapper = null;
    }
}
