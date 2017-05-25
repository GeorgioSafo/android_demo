package demo.georgiosafo.com.androiddemo.data.model.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsLocalData extends RealmObject {
    @PrimaryKey
    private String localId;
    private String title;
    private String date;
    private String post;

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
