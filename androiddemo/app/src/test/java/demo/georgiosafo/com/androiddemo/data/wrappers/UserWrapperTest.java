/**
 * Copyright (C) 2017 Gevork Safaryan Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.georgiosafo.com.androiddemo.data.wrappers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;

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
