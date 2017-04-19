package demo.georgiosafo.com.androiddemo.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.network.AndroidDemoApi;
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
        return new Retrofit.Builder()
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

}
