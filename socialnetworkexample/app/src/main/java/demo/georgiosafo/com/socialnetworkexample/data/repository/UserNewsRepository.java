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

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserNewsDataLocalStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserNewsDataMemoryStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserNewsDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.wrappers.UserNewsWrapper;
import rx.Observable;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsRepository implements IDataRepository<List<UserNewsLocalData>> {
    private final IDataLocalStore<List<UserNewsLocalData>> localStore;
    private final IDataMemoryStore<List<UserNewsLocalData>> memoryStore;
    private final IDataRemoteStore<List<UserNewsRemoteData>> remoteStore;

    /**
     * Constructs a {@link UserNewsRepository}.
     *
     * @param localStore  {@link UserNewsDataLocalStore}.
     * @param remoteStore {@link UserNewsDataRemoteStore}.
     * @param remoteStore {@link UserNewsDataMemoryStore}.
     */


    public UserNewsRepository(IDataLocalStore<List<UserNewsLocalData>> localStore,
                              IDataRemoteStore<List<UserNewsRemoteData>> remoteStore,
                              IDataMemoryStore<List<UserNewsLocalData>> memoryStore) {
        this.localStore = localStore;
        this.memoryStore = memoryStore;
        this.remoteStore = remoteStore;
    }

    @Override
    public Observable<List<UserNewsLocalData>> getNetworkData(HashMap<String, Object> map) {
        return remoteStore.getDataWithParams(map)
                .map(userRemoteDataList -> new UserNewsWrapper().wrapUserRemoteData(userRemoteDataList))
                .map(userLocalData -> {
                    Collections.sort(userLocalData, (uld1, uld2) -> uld2.getDate().compareTo(uld1.getDate()));
                    return userLocalData;
                })
                .doOnNext(userNewsLocalData -> {
                    if (userNewsLocalData != null && userNewsLocalData.size() > 0) {
                        memoryStore.setData(userNewsLocalData);
                        localStore.saveData(userNewsLocalData);
                    }
                });
    }

    @Override
    public Observable<List<UserNewsLocalData>> getLocalData(HashMap<String, Object> map) {
        return localStore.getDataWithParams(map).doOnNext(memoryStore::setData);
    }

    @Override
    public List<UserNewsLocalData> getMemoryData(HashMap<String, Object> map) {
        return memoryStore.getDatawithParams(map);
    }

    @Override
    public boolean isCached() {
        return memoryStore.isCached();
    }
}