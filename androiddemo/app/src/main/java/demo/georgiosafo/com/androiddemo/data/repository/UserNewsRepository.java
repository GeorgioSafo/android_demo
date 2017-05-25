package demo.georgiosafo.com.androiddemo.data.repository;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.wrappers.UserNewsWrapper;
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