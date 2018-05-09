package com.apps.skadied.girlshub.models;
import io.realm.RealmObject;

/**
 * Created by skadied on 05-08-18.
 */

public class ClientModel extends RealmObject{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
