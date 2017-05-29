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
package demo.georgiosafo.com.socialnetworkexample.data.database;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
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
