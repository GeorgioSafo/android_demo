package demo.georgiosafo.com.androiddemo.presentation.view.interfaces;

import android.support.v4.util.Pair;
import android.view.View;

import java.util.List;

import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public interface MainView extends BaseView {
    void showUsers(List<UserLocalData> userLocalDatas);

    void startTransition(Pair<View, String>[] pair, UserLocalData user);
}
