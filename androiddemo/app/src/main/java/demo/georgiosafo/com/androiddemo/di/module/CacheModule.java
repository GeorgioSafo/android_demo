package demo.georgiosafo.com.androiddemo.di.module;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataMemoryStore;

/**
 * Created by gevorksafaryan on 22.05.17.
 */
@Module
public class CacheModule {

    @Provides
    @Singleton
    IDataMemoryStore<List<UserLocalData>> provideUserDataMemoryStore() {
        return new UserDataMemoryStore();
    }

    @Provides
    @Singleton
    IDataMemoryStore<List<UserNewsLocalData>> provideUserNewsMemoryStore() {
        return new UserNewsDataMemoryStore();
    }

}
