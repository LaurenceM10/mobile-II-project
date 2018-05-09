package com.apps.skadied.girlshub.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.PersonModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call the necessary methods
        initViews();
        configureViewElements();
        setupRecyclerView();
        initActions();
        //fetchHtppRequest();
    }

    /**
     * To get references of the view elements
     */
    private void initViews() {
        floatingActionButton = findViewById(R.id.floating_action_button);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    /**
     * To cofigure some view elements
     */
    private void configureViewElements() {
        setSupportActionBar(toolbar);
    }

    /**
     * Setup the recycler view
     */
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * To initiate actions when events occur on the elements
     */

    private void initActions() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    /**
     * To make an http request
     */
    /*private void fetchHtppRequest() {
        Call<List<PeoplenModel>> call = Api.instance().listPeople();
        call.enqueue(new Callback<List<PersonModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PersonModel>> call, @NonNull Response<List<PersonModel>> response) {
                if (response.body() != null) {
                    List<PersonModel> people = response.body();

                    PersonAdaasdpter eventAdaasdpter = new PersoasdnAdapter(pesople, MainActivity.this);
                    recyclerView.setAdapter(eventAdapter);

                    assert people != null;
                    for (PersonModel personModel : people) {
                        Log.i("name: ", personModel.getName());
                        Log.i("photo_url: ", personModel.getPhoto_url());
                        Log.i("age: ", personModel.getAge());
                        Log.i("phone: ", personModel.getPhone());
                        Log.i("address: ", personModel.getAddress());
                        Log.i("-----------------", "");
                    }
                } else {
                    Log.i("Debug: ", "Null response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PersonModel>> call, @NonNull Throwable t) {
                Log.e("Debug: ", "Error");
            }
        });
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //fetchHtppRequest();
    }
}
