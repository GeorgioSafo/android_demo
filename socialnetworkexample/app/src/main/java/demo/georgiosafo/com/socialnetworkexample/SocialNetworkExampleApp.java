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
package demo.georgiosafo.com.socialnetworkexample;

import android.app.Application;

import demo.georgiosafo.com.socialnetworkexample.di.component.DaggerSocialNetworkExampleAppComponent;
import demo.georgiosafo.com.socialnetworkexample.di.component.SocialNetworkExampleAppComponent;
import demo.georgiosafo.com.socialnetworkexample.di.module.LocalModule;
import demo.georgiosafo.com.socialnetworkexample.di.module.NetworkModule;
import demo.georgiosafo.com.socialnetworkexample.di.module.SocialNetworkExampleAppModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class SocialNetworkExampleApp extends Application {

    private static SocialNetworkExampleApp socialNetworkExampleApp;
    private static SocialNetworkExampleAppComponent socialNetworkExampleAppComponent;

    public SocialNetworkExampleAppComponent getAppComponent() {
        return socialNetworkExampleAppComponent;
    }

    public static SocialNetworkExampleApp getSocialNetworkExampleApp() {
        return socialNetworkExampleApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        socialNetworkExampleApp = this;
        socialNetworkExampleAppComponent = DaggerSocialNetworkExampleAppComponent.builder()
                .socialNetworkExampleAppModule(getSocialNetworkExampleAppModule())
                .networkModule(new NetworkModule())
                .localModule(new LocalModule())
                .build();
        socialNetworkExampleAppComponent.inject(this);
        initRealm();
    }


    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    protected SocialNetworkExampleAppModule getSocialNetworkExampleAppModule() {
        return new SocialNetworkExampleAppModule(this);
    }
}

