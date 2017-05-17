package demo.georgiosafo.com.androiddemo.di.module;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
import demo.georgiosafo.com.androiddemo.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserDataRemoteStore;
import demo.georgiosafo.com.androiddemo.data.repository.store.UserNewsDataRemoteStore;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

@Module
public class NetworkModule {

    public static final String BASE_URL = "https://gist.githubusercontent.com/GeorgioSafo/";


    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }


    @Provides
    @Singleton
    AndroidDemoApi provideApi(Retrofit retrofit) {
        return retrofit.create(AndroidDemoApi.class);
    }

    @Provides
    IDataRemoteStore<ArrayList<UserLocalData>> provideRemoteStore(AndroidDemoApi api) {
        return new UserDataRemoteStore(api);
    }

    @Provides
    IDataRemoteStore<ArrayList<UserNewsLocalData>> proviewUserNewsRemoteStore(AndroidDemoApi api) {
        return new UserNewsDataRemoteStore(api);
    }

}
