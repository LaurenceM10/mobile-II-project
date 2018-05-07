package com.apps.skadied.eventsapp.api;

import com.apps.skadied.eventsapp.models.CreateEvent;
import com.apps.skadied.eventsapp.models.EventModel;

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
    @GET("events")
    Call<List<EventModel>> listEvents();

    @POST("events")
    Call<EventModel> createEvent(@Body CreateEvent event);

    @PUT("events/{id}")
    Call<EventModel> updateEvent(@Path("id") int id, @Body CreateEvent eventModel);

    @DELETE("events/{id}")
    Call<EventModel> deleteEvent(@Path("id") int id);
}
