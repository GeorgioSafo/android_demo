package demo.georgiosafo.com.androiddemo.di.module;

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.androiddemo.data.repository.UserRepository;
import demo.georgiosafo.com.androiddemo.di.scope.PerActivity;
import demo.georgiosafo.com.androiddemo.domain.interactor.UserInteractor;
import demo.georgiosafo.com.androiddemo.domain.interactor.interfaces.IUserInteractor;
import demo.georgiosafo.com.androiddemo.presentation.presenters.MainPresenter;
import demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.MainView;

/**
 * Created by gevorksafaryan on 19.04.17.
 */
@Module
public class MainModule {

    MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    @PerActivity
    MainView provideMainView() {
        return mainView;
    }

    @Provides
    @PerActivity
    IMainPresenter provideMainPresenter(MainView view,IUserInteractor interactor) {
        return new MainPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    IUserInteractor provideUserInteractor(UserRepository repository) {
        return new UserInteractor(repository);
    }
}
