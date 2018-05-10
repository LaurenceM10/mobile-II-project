package com.apps.skadied.girlshub.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.CreatePerson;
import com.apps.skadied.girlshub.models.PersonModel;
import com.apps.skadied.girlshub.ui.fragments.PeopleFragment;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPersonActivity extends AppCompatActivity {
    private Intent intent;
    private TextInputEditText name;
    private TextInputEditText photo_url;
    private TextInputEditText age;
    private TextInputEditText career;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        initViews();
        initActions();
    }


    /**
     * To init views
     */
    private void initViews() {
        name = findViewById(R.id.name);
        photo_url = findViewById(R.id.photo_url);
        age = findViewById(R.id.age);
        career = findViewById(R.id.career);
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
        CreatePerson person = new CreatePerson();
        person.setName(name.getText().toString());
        person.setPhoto_url(photo_url.getText().toString());
        person.setAge(age.getText().toString());
        person.setCareer(career.getText().toString());

        Call<PersonModel> call = Api.instance().createPerson(person);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(@NonNull Call<PersonModel> call, @NonNull Response<PersonModel> response) {
                if (response.body() != null) {

                    Log.i("name: ", response.body().getName());
                    Log.i("photo_url: ", response.body().getPhoto_url());
                    Log.i("age: ", response.body().getAge());
                    Log.i("career: ", response.body().getCareer());

                    finish();
                } else {
                    Log.i("Debug", " Null Response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PersonModel> call, @NonNull Throwable t) {
                Log.i("Debug", "Error");
            }
        });

    }
}
