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
package demo.georgiosafo.com.socialnetworkexample.di.module;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.network.SocialNetworkExampleApi;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserDataRemoteStore;
import demo.georgiosafo.com.socialnetworkexample.data.repository.store.UserNewsDataRemoteStore;
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
    SocialNetworkExampleApi provideApi(Retrofit retrofit) {
        return retrofit.create(SocialNetworkExampleApi.class);
    }

    @Provides
    IDataRemoteStore<List<UserRemoteData>> provideRemoteStore(SocialNetworkExampleApi api) {
        return new UserDataRemoteStore(api);
    }

    @Provides
    IDataRemoteStore<List<UserNewsRemoteData>> proviewUserNewsRemoteStore(SocialNetworkExampleApi api) {
        return new UserNewsDataRemoteStore(api);
    }

}
