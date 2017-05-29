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
import demo.georgiosafo.com.socialnetworkexample.data.repository.UserNewsRepository;
import demo.georgiosafo.com.socialnetworkexample.di.scope.PerActivity;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.UserNewsInteractor;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.interfaces.IUserNewsInteractor;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.UserInfoPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.UserInfoView;

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
