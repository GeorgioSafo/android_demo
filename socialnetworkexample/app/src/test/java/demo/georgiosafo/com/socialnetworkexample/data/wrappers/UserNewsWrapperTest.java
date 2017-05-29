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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;

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
