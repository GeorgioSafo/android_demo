package demo.georgiosafo.com.androiddemo.di.module;

import android.app.Application;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;

/**
 * Created by gevorksafaryan on 19.04.17.
 */
@Module
public class AndroidDemoAppModule {
    private Application application;

    public AndroidDemoAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(IDataLocalStore<List<UserLocalData>> localStore, IDataRemoteStore<List<UserRemoteData>> remoteStore, IDataMemoryStore<List<UserLocalData>> memoryStore) {
        return new UserRepository(localStore, remoteStore, memoryStore);
    }

    @Provides
    @Singleton
    UserNewsRepository provideUserNewsRepository(IDataLocalStore<List<UserNewsLocalData>> localStore, IDataRemoteStore<List<UserNewsRemoteData>> remoteStore, IDataMemoryStore<List<UserNewsLocalData>> memoryStore) {
        return new UserNewsRepository(localStore, remoteStore, memoryStore);
    }
}
