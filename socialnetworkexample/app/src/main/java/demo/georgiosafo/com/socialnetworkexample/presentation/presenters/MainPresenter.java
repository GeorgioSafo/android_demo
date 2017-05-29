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

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserLocalData;
import demo.georgiosafo.com.socialnetworkexample.domain.interactor.interfaces.IUserInteractor;
import demo.georgiosafo.com.socialnetworkexample.presentation.presenters.interfaces.IMainPresenter;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.MainView;
import rx.Subscriber;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public class MainPresenter implements IMainPresenter {

    private MainView mainView;
    private IUserInteractor interactor;

    public MainPresenter(MainView mainView, IUserInteractor interactor) {
        this.mainView = mainView;
        this.interactor = interactor;
    }

    @Override
    public void loadUsers() {
        interactor.getUserList(subscriber);
    }

    @Override
    public void reloadData() {

    }

    @Override
    public void onDestroy() {
        interactor.unsubscribe();
        mainView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private Subscriber<List<UserLocalData>> subscriber = new Subscriber<List<UserLocalData>>() {
        @Override
        public void onStart() {
            mainView.showProgress();
        }


        @Override
        public void onCompleted() {
            mainView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            mainView.hideProgress();
            mainView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<UserLocalData> userLocalDatas) {
            mainView.hideProgress();
            mainView.showUsers(userLocalDatas);
        }
    };
}
