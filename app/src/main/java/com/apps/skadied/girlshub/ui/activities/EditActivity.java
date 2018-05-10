package com.apps.skadied.girlshub.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.CreatePerson;
import com.apps.skadied.girlshub.models.PersonModel;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    private TextInputEditText name;
    private TextInputEditText photo_url;
    private TextInputEditText age;
    private TextInputEditText phone;
    private TextInputEditText address;
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
        name = findViewById(R.id.name);
        photo_url = findViewById(R.id.photo_url);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
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
        person.setPhoto_url(photo_url.getText().toString());
        person.setAge(age.getText().toString());
        //person.setCareer();

        Call<PersonModel> call = Api.instance().updatePerson(id, person);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                Toast.makeText(EditActivity.this, "Modificado con exito", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
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
