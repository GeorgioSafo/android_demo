package demo.georgiosafo.com.androiddemo.di.component;

import dagger.Component;
import demo.georgiosafo.com.androiddemo.MainActivity;
import demo.georgiosafo.com.androiddemo.di.module.MainModule;
import demo.georgiosafo.com.androiddemo.di.scope.PerActivity;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IMainPresenter;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

@PerActivity
@Component(dependencies = AndroidDemoAppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
    IMainPresenter mainPresenter();
}
