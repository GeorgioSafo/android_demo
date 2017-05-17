package demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public interface IUserInfoPresenter extends IBasePresenter {
    void loadUser(String userId);

    void completeFields(UserLocalData localData);
}
