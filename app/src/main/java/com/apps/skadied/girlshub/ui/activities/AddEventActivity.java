package com.apps.skadied.eventsapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apps.skadied.eventsapp.R;
import com.apps.skadied.eventsapp.api.Api;
import com.apps.skadied.eventsapp.models.CreateEvent;
import com.apps.skadied.eventsapp.models.EventModel;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText description;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        initViews();
        initActions();
    }

    /**
     * To init views
     */
    private void initViews() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        save = findViewById(R.id.save);
    }

    /**
     * To init actions
     */
    private void initActions() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchHttpRequest();
            }
        });
    }

    /**
     * to make an http request
     */
    private void fetchHttpRequest() {
        CreateEvent event = new CreateEvent();
        event.setTitle(title.getText().toString());
        event.setDescription(description.getText().toString());
        event.setCreatedAt(getCurrentDate());

        Call<EventModel> call = Api.instance().createEvent(event);
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(@NonNull Call<EventModel> call, @NonNull Response<EventModel> response) {
                if (response.body() != null) {
                    Log.i("title: ", response.body().getTitle());
                    Log.i("description: ", response.body().getDescription());
                    Log.i("created_at: ", response.body().getCreatedAt());

                    finish();
                } else {
                    Log.i("Debug", " Null Response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventModel> call, @NonNull Throwable t) {
                Log.i("Debug", "Error");
            }
        });

    }


    /**
     * To get the current date
     */
    private String getCurrentDate() {
        return DateFormat.getDateInstance().format(new Date());
    }
}
