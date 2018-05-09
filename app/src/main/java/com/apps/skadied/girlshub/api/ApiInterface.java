package com.apps.skadied.girlshub.api;

import com.apps.skadied.girlshub.models.ClientModel;
import com.apps.skadied.girlshub.models.CreateLike;
import com.apps.skadied.girlshub.models.CreatePerson;
import com.apps.skadied.girlshub.models.LikeModel;
import com.apps.skadied.girlshub.models.PeopleModel;
import com.apps.skadied.girlshub.models.PersonModel;
import com.apps.skadied.girlshub.models.AccessTokenModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("people")
    Call<List<PeopleModel>> listPeople();


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

    @POST("Client")
    Call<ClientModel> createClient(@Body ClientModel client);

    @POST("Clients/login")
    Call<AccessTokenModel> login(@Body ClientModel client);

}
