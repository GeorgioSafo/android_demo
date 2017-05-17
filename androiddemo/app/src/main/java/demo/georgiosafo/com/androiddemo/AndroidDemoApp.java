package demo.georgiosafo.com.androiddemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import demo.georgiosafo.com.androiddemo.di.component.AndroidDemoAppComponent;
import demo.georgiosafo.com.androiddemo.di.component.DaggerAndroidDemoAppComponent;
import demo.georgiosafo.com.androiddemo.di.module.AndroidDemoAppModule;
import demo.georgiosafo.com.androiddemo.di.module.LocalModule;
import demo.georgiosafo.com.androiddemo.di.module.NetworkModule;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class AndroidDemoApp extends Application {

    private static AndroidDemoApp androidDemoApp;
    private static AndroidDemoAppComponent androidDemoAppComponent;

    public AndroidDemoAppComponent getAppComponent() {
        return androidDemoAppComponent;
    }

    public static AndroidDemoApp getAndroidDemoApp() {
        return androidDemoApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        androidDemoApp = this;
        androidDemoAppComponent = DaggerAndroidDemoAppComponent.builder()
                .androidDemoAppModule(getAndroidDemoAppModule())
                .networkModule(new NetworkModule())
                .localModule(new LocalModule())
                .build();
        androidDemoAppComponent.inject(this);
        Fresco.initialize(this);
    }


    protected AndroidDemoAppModule getAndroidDemoAppModule() {
        return new AndroidDemoAppModule(this);
    }
}

