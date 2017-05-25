package demo.georgiosafo.com.androiddemo.di.component;

import dagger.Component;
import demo.georgiosafo.com.androiddemo.UserInfoActivity;
import demo.georgiosafo.com.androiddemo.di.module.UserInfoModule;
import demo.georgiosafo.com.androiddemo.di.scope.PerActivity;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

@PerActivity
@Component(dependencies = AndroidDemoAppComponent.class, modules = UserInfoModule.class)
public interface UserInfoComponent {
    void inject(UserInfoActivity userInfoActivity);
    IUserInfoPresenter userInfoPresenter();
}
