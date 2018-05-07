package com.apps.skadied.eventsapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apps.skadied.eventsapp.R;
import com.apps.skadied.eventsapp.api.Api;
import com.apps.skadied.eventsapp.models.CreateEvent;
import com.apps.skadied.eventsapp.models.EventModel;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText description;
    private Button save;

    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = getIntent().getIntExtra("id", 0);

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

        Call<EventModel> call = Api.instance().updateEvent(id, event);
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                Toast.makeText(EditActivity.this, "Modificado con exito", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Toast.makeText(EditActivity.this, "No se pudo modificar", Toast.LENGTH_SHORT).show();
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
