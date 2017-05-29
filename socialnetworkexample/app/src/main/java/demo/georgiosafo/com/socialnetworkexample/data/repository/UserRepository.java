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
package demo.georgiosafo.com.socialnetworkexample.data.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserDataLocalStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserDataMemoryStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.wrappers.UserWrapper;
import rx.Observable;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class UserRepository implements IDataRepository<List<UserLocalData>> {
    private final IDataLocalStore<List<UserLocalData>> localStore;
    private final IDataRemoteStore<List<UserRemoteData>> remoteStore;
    private IDataMemoryStore<List<UserLocalData>> memoryStore;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param localStore  {@link UserDataLocalStore}.
     * @param remoteStore {@link UserDataRemoteStore}.
     * @param memoryStore {@link UserDataMemoryStore}.
     */
    public UserRepository(IDataLocalStore<List<UserLocalData>> localStore, IDataRemoteStore<List<UserRemoteData>> remoteStore, IDataMemoryStore<List<UserLocalData>> memoryStore) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
        this.memoryStore = memoryStore;
    }

    @Override
    public Observable<List<UserLocalData>> getNetworkData(HashMap<String, Object> map) {
        return remoteStore.getDataWithParams(map)
                .map(userRemoteDataList -> new UserWrapper().wrapUserRemoteData(userRemoteDataList))
                .map(userLocalData -> {
                    Collections.sort(userLocalData, (uld1, uld2) -> uld2.getUserNews().getDate().compareTo(uld1.getUserNews().getDate()));
                    return userLocalData;
                })
                .doOnNext(userLocalData -> {
                    if (userLocalData != null && userLocalData.size() > 0) {
                        memoryStore.setData(userLocalData);
                        localStore.saveData(userLocalData);
                    }
                });
    }

    @Override
    public Observable<List<UserLocalData>> getLocalData(HashMap<String, Object> map) {
        return localStore.getDataWithParams(map).doOnNext(memoryStore::setData);
    }

    @Override
    public List<UserLocalData> getMemoryData(HashMap<String, Object> map) {
        return memoryStore.getDatawithParams(map);
    }

    @Override
    public boolean isCached() {
        return memoryStore.isCached();
    }
}
