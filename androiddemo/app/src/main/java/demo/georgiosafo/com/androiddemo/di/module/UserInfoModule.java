package demo.georgiosafo.com.androiddemo.di.module;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.di.scope.PerActivity;
import demo.georgiosafo.com.androiddemo.domain.interactor.UserInteractor;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
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
    IUserInfoPresenter provideUserInfoPresenter(UserInfoView view, IUserInteractor interactor) {
        return new UserInfoPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    IUserInteractor provideUserInteractor(UserRepository repository) {
        return new UserInteractor(repository);
    }
}
