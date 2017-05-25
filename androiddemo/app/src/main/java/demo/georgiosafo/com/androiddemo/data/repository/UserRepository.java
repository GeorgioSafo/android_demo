package demo.georgiosafo.com.androiddemo.data.repository;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRepository;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataLocalStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataMemoryStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.wrappers.UserWrapper;
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
