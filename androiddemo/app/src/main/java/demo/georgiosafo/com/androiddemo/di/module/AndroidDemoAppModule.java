package demo.georgiosafo.com.androiddemo.di.module;

import android.app.Application;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
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
    UserRepository provideUserRepository(IDataLocalStore<ArrayList<UserLocalData>> localStore, IDataRemoteStore<ArrayList<UserLocalData>> remoteStore) {
        return new UserRepository(localStore, remoteStore);
    }

    @Provides
    @Singleton
    UserNewsRepository provideUserNewsRepository(IDataLocalStore<ArrayList<UserNewsLocalData>> localStore, IDataRemoteStore<ArrayList<UserNewsLocalData>> remoteStore) {
        return new UserNewsRepository(localStore, remoteStore);
    }
}
