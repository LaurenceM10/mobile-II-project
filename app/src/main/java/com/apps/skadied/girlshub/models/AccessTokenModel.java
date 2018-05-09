package com.apps.skadied.girlshub.models;

import io.realm.RealmObject;

/**
 * Created by skadied on 05-08-18.
 */

public class AccessTokenModel extends RealmObject {

    private String userId;
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
