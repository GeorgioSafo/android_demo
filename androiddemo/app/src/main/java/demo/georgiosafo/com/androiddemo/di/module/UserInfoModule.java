package demo.georgiosafo.com.androiddemo.di.module;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.repository.UserNewsRepository;
import demo.georgiosafo.com.androiddemo.di.scope.PerActivity;
import demo.georgiosafo.com.androiddemo.domain.interactor.UserNewsInteractor;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserNewsInteractor;
import demo.georgiosafo.com.androiddemo.presentation.presenters.UserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;

/**
 * Created by gevorksafaryan on 22.04.17.
 */
@Module
public class UserInfoModule {

    UserInfoView userInfoView;

    public UserInfoModule(UserInfoView userInfoView) {
        this.userInfoView = userInfoView;
    }

    @Provides
    @PerActivity
    UserInfoView provideUserInfoView() {
        return userInfoView;
    }

    @Provides
    @PerActivity
    IUserInfoPresenter provideUserInfoPresenter(UserInfoView view, IUserNewsInteractor interactor) {
        return new UserInfoPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    IUserNewsInteractor provideUserInteractor(UserNewsRepository repository) {
        return new UserNewsInteractor(repository);
    }
}
