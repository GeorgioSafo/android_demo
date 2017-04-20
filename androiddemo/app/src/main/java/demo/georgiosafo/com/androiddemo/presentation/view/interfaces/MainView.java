package demo.georgiosafo.com.androiddemo.presentation.view.interfaces;

import java.util.ArrayList;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public interface MainView extends BaseView{
    void showUsers(ArrayList<UserLocalData> userLocalDatas);
}
