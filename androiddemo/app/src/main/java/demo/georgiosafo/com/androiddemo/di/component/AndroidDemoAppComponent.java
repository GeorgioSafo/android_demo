package demo.georgiosafo.com.androiddemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import demo.georgiosafo.com.androiddemo.AndroidDemoApp;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.di.module.AndroidDemoAppModule;
import demo.georgiosafo.com.androiddemo.di.module.CacheModule;
import demo.georgiosafo.com.androiddemo.di.module.LocalModule;
import demo.georgiosafo.com.androiddemo.di.module.NetworkModule;

/**
 * Created by gevorksafaryan on 19.04.17.
 */
@Singleton
@Component(
        modules = {AndroidDemoAppModule.class, NetworkModule.class, LocalModule.class, CacheModule.class}
)
public interface AndroidDemoAppComponent {
    void inject(AndroidDemoApp app);

    UserRepository userRepository();

    UserNewsRepository userNewsRepository();

}
