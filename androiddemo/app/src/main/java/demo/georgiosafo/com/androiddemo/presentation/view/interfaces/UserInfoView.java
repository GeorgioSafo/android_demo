package demo.georgiosafo.com.androiddemo.presentation.view.interfaces;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public interface UserInfoView extends BaseView {
    void setTitle(String title);
    void setSubTitle(String subTitle);
    void setAvatar(String Url);
    void setBackground(String Url);
    void setHistory();
    void setHeaderText(String s);
}
