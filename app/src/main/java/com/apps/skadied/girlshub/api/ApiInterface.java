package com.apps.skadied.girlshub.api;

import com.apps.skadied.girlshub.models.CreateLike;
import com.apps.skadied.girlshub.models.CreatePerson;
import com.apps.skadied.girlshub.models.LikeModel;
import com.apps.skadied.girlshub.models.PersonModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Administrador on 6/3/2018.
 */

public interface ApiInterface {
    @GET("people")
    Call<List<PersonModel>> listPeople();

    @POST("people")
    Call<PersonModel> createPerson(@Body CreatePerson person);

    @PUT("people/{id}")
    Call<PersonModel> updatePerson(@Path("id") int id, @Body CreatePerson personModel);

    @DELETE("people/{id}")
    Call<PersonModel> deletePerson(@Path("id") int id);

    @GET("people/{id}/likes")
    Call<List<PersonModel>> listLikes();

    @POST("likes")
    Call<LikeModel> createLike(@Body CreateLike like);

    @DELETE("likes")
    Call<LikeModel> deleteLike(@Path("id") int id);

    @POST("user")
    Call<PersonModel> createUser(@Body CreatePerson person);

}
