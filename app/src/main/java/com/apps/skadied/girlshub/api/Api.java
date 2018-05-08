package com.apps.skadied.girlshub.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrador on 6/3/2018.
 */

public class Api {
    private final static String URL = "https://girlshub-api.herokuapp.com/api";

    public static String getBase() {
        return URL + "/";
    }

    public static ApiInterface instance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
