package demo.georgiosafo.com.androiddemo.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gevorksafaryan on 19.04.17.
 */
@Module
public class AndroidDemoAppModule {
    private Application application;

    public AndroidDemoAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
