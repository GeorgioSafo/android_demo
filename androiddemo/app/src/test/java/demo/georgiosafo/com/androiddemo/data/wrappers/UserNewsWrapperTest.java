/**
 * Copyright (C) 2017 Gevork Safaryan Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.georgiosafo.com.androiddemo.data.wrappers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by gevorksafaryan on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserNewsWrapperTest {

    private static final String FAKE_ID = "wr3wr3w43gfc";
    private static final String FAKE_DATE = "19-04-2017T14:56:20";
    private static final String FAKE_POST = "After a hacking scare at Gawker Media last year, security firm Duo Security showed that...";
    private static final String FAKE_TITLE = "title";

    UserNewsWrapper userNewsWrapper;

    @Before
    public void setUp() {
        userNewsWrapper = new UserNewsWrapper();
    }

    @Test
    public void testWrapUserNewsRemoteData() {
        //arrange
        UserNewsRemoteData userNewsRemoteData = createFakeUserNewsRemoteData();

        //act
        UserNewsLocalData userNewsLocalData = userNewsWrapper.wrapUserRemoteData(userNewsRemoteData);

        //assert
        assertThat(userNewsLocalData, is(instanceOf(UserNewsLocalData.class)));
        assertThat(userNewsLocalData.getLocalId(), is(FAKE_ID));
        assertThat(userNewsLocalData.getDate(), is(FAKE_DATE));
        assertThat(userNewsLocalData.getPost(), is(FAKE_POST));
        assertThat(userNewsLocalData.getTitle(), is(FAKE_TITLE));
    }

    @Test
    public void testTransformUserEntityCollection() {
        //arrange
        UserNewsRemoteData mockUserNewsRemoteDataFirst = mock(UserNewsRemoteData.class);
        UserNewsRemoteData mockUserNewsRemoteDataSecond = mock(UserNewsRemoteData.class);

        ArrayList<UserNewsRemoteData> userNewsRemoteDataArrayList = new ArrayList<>(10);
        userNewsRemoteDataArrayList.add(mockUserNewsRemoteDataFirst);
        userNewsRemoteDataArrayList.add(mockUserNewsRemoteDataSecond);

        //act
        List<UserNewsLocalData> userNewsLocalDataList = userNewsWrapper.wrapUserRemoteData(userNewsRemoteDataArrayList);

        //assert
        assertThat(userNewsLocalDataList.toArray()[0], is(instanceOf(UserNewsLocalData.class)));
        assertThat(userNewsLocalDataList.toArray()[1], is(instanceOf(UserNewsLocalData.class)));
        assertThat(userNewsLocalDataList.size(), is(2));
    }

    private UserNewsRemoteData createFakeUserNewsRemoteData() {
        UserNewsRemoteData userNewsRemoteData = new UserNewsRemoteData();
        userNewsRemoteData.setServerId(FAKE_ID);
        userNewsRemoteData.setDate(FAKE_DATE);
        userNewsRemoteData.setPost(FAKE_POST);
        userNewsRemoteData.setTitle(FAKE_TITLE);
        return userNewsRemoteData;
    }
}
