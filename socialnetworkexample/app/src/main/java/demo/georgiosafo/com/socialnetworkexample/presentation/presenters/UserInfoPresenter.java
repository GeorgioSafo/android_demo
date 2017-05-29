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
package demo.georgiosafo.com.socialnetworkexample.presentation.presenters;

import java.util.List;
import java.util.Locale;

import demo.georgiosafo.com.socialnetworkexample.R;
import demo.georgiosafo.com.socialnetworkexample.SocialNetworkExampleApp;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.interfaces.IUserNewsInteractor;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IUserInfoPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.UserInfoView;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public class UserInfoPresenter implements IUserInfoPresenter {
    private UserInfoView view;
    private IUserNewsInteractor interactor;

    public UserInfoPresenter(UserInfoView view, IUserNewsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {
        interactor.unsubscribe();
        view = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
        subscriber.unsubscribe();
    }

    @Override
    public void loadUser(String userId) {
        interactor.getUserNewsList(subscriber);
    }

    @Override
    public void completeFields(UserLocalData localData) {
        view.setBackground(localData.getAvatarUrl());
        view.setAvatar(localData.getAvatarUrl());
        view.setTitle(String.format(Locale.getDefault(), SocialNetworkExampleApp.getSocialNetworkExampleApp().getResources().getString(R.string.name_format), localData.getFirstName(), localData.getLastName()));
        view.setSubTitle(String.valueOf(localData.getAge()));

        StringBuilder sb = new StringBuilder()
                .append("Age: ")
                .append(String.valueOf(localData.getAge()))
                .append(System.getProperty("line.separator"))
                .append("E-mail: ").append(localData.getEmail());
        view.setHeaderText(sb.toString());
    }

    private Subscriber<List<UserNewsLocalData>> subscriber = new Subscriber<List<UserNewsLocalData>>() {
        @Override
        public void onStart() {
            view.showProgress();
        }


        @Override
        public void onCompleted() {
            view.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            view.hideProgress();
            view.showError(e.getMessage());
        }

        @Override
        public void onNext(List<UserNewsLocalData> userLocalDatas) {
            view.hideProgress();
            view.setHistory(userLocalDatas);
        }
    };
}
