package demo.georgiosafo.com.androiddemo.data.network;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import rx.Observable;

/**
 * Created by gevorksafaryan on 23.05.17.
 */

public class MockAndroidDemoApi implements AndroidDemoApi {


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
