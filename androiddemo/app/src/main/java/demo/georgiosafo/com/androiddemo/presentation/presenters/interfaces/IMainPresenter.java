package demo.georgiosafo.com.androiddemo.presentation.presenters.interfaces;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public interface IMainPresenter {
    void loadUsers();

    void reloadData();

    void onClick(String sender_id);

    void onDestroy();

    void onResume();

    void onPause();
}
