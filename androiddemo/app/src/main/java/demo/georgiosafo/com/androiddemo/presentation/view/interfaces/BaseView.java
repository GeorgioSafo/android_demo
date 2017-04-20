package demo.georgiosafo.com.androiddemo.presentation.view.interfaces;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

interface BaseView {
    void showProgress();

    void showError(String errorMessage);

    void hideProgress();
}
