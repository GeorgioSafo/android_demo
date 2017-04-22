package demo.georgiosafo.com.androiddemo.di.module;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataLocalStore;

/**
 * Created by gevorksafaryan on 22.04.17.
 */
@Module
public class LocalModule {

    @Provides
    IDataLocalStore<ArrayList<UserLocalData>> provideLocalStore(AndroidDemoApi api) {
        return new UserDataLocalStore();
    }
}
