package demo.georgiosafo.com.androiddemo.data.database;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by gevorksafaryan on 22.05.17.
 */

public class RealmService implements IRealmService {
    private final Realm realm;

    public RealmService(final Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<List<UserLocalData>> getAllUserLocalData() {
        return realm.allObjects(UserLocalData.class).asObservable().map(userLocalData -> {
            RealmResults<UserLocalData> localData = realm.allObjects(UserLocalData.class);
            return localData.subList(0, localData.size());
        });

    }

    @Override
    public void updateUserLocalData(final List<UserLocalData> data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(data);
                    realm.commitTransaction();
                }
        );
    }

    @Override
    public void closeRealm() {
        realm.close();
    }


    @Override
    public Observable<List<UserNewsLocalData>> getNews() {
        return realm.allObjects(UserNewsLocalData.class).asObservable().map(userLocalData -> {
            RealmResults<UserNewsLocalData> localData = realm.allObjects(UserNewsLocalData.class);
            return localData.subList(0, localData.size());
        });
    }

    @Override
    public void updateUserNewsData(List<UserNewsLocalData> data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(data);
                    realm.commitTransaction();
                }
        );
    }
}
