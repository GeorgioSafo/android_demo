package demo.georgiosafo.com.androiddemo.data.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gevorksafaryan on 18.04.17.
 */

public class UserRemoteData {

    @SerializedName("user_id")
    private String serverId;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("email")
    private String email;

    @SerializedName("age")
    private Integer age;

    @SerializedName("avatar")
    private String avatarUrl;

    public String getServerId() {
        return serverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
