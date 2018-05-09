package com.apps.skadied.girlshub.models;

import io.realm.RealmObject;

/**
 * Created by Lauren Steven on 9/5/2018.
 */
public class PeopleModel extends RealmObject {
    private int id;
    private String name;
    private String photo_url;
    private String age;
    private String career;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
