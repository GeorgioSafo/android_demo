package demo.georgiosafo.com.androiddemo.di.module;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataLocalStore;

/**
 * Created by gevorksafaryan on 22.04.17.
 */
@Module
public class LocalModule {

    @Provides
    IDataLocalStore<ArrayList<UserLocalData>> provideLocalStore() {
        return new UserDataLocalStore();
    }

    @Provides
    IDataLocalStore<ArrayList<UserNewsLocalData>> provideUserNewsLocalStore() {
        return new UserNewsDataLocalStore();
    }
}
