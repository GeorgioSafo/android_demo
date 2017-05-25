package demo.georgiosafo.com.androiddemo.di.module;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.database.IRealmService;
import demo.georgiosafo.com.androiddemo.data.database.RealmService;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataLocalStore;
import io.realm.Realm;

/**
 * Created by gevorksafaryan on 22.04.17.
 */
@Module
public class LocalModule {

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    IRealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }


    @Provides
    IDataLocalStore<List<UserLocalData>> provideLocalStore(IRealmService realmService) {
        return new UserDataLocalStore(realmService);
    }

    @Provides
    IDataLocalStore<List<UserNewsLocalData>> provideUserNewsLocalStore(IRealmService realmService) {
        return new UserNewsDataLocalStore(realmService);
    }
}
