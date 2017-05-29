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

import dagger.Module;
import dagger.Provides;
import demo.georgiosafo.com.socialnetworkexample.data.repository.UserRepository;
import demo.georgiosafo.com.socialnetworkexample.di.scope.PerActivity;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.UserInteractor;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.interfaces.IUserInteractor;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.MainPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.MainView;

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
    IMainPresenter provideMainPresenter(MainView view, IUserInteractor interactor) {
        return new MainPresenter(view, interactor);
    }

    @Provides
    @PerActivity
    IUserInteractor provideUserInteractor(UserRepository repository) {
        return new UserInteractor(repository);
    }
}
