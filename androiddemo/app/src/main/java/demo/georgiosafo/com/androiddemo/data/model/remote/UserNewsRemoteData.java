package demo.georgiosafo.com.androiddemo.data.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsRemoteData {

    @SerializedName("id")
    private String serverId;

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private String date;

    @SerializedName("post")
    private String post;

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

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
