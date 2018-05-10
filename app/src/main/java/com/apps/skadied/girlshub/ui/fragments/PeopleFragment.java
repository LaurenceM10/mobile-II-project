package com.apps.skadied.girlshub.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.PeopleModel;
import com.apps.skadied.girlshub.ui.adapters.GirlAdapter;
import com.apps.skadied.girlshub.ui.adapters.GirlAdapterFromDatabase;
import com.tumblr.remember.Remember;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private static final String IS_FIRST_TIME = "is_first_time";

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        super.onResume();
            fetchGirls();
            storeFirstTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupRecyclerView();

        if (!isFirstTime()) {
            fetchGirls();
        } else {
            Log.i("Step zero", "Debug");
            fetchGirls();
            storeFirstTime();
            progressBar.setVisibility(View.GONE);
        }
    }

    private void storeFirstTime() {
        Remember.putBoolean(IS_FIRST_TIME, true);
    }

    private boolean isFirstTime() {
        return Remember.getBoolean(IS_FIRST_TIME, false);
    }

    /**
     * Init views
     */
    private void initViews(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    /**
     * Setup recyclerview
     */
    private void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    /**
     * To fetch girsls
     */
    private void fetchGirls(){
        Call<List<PeopleModel>> call = Api.instance().listPeople();
        call.enqueue(new Callback<List<PeopleModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PeopleModel>> call, @NonNull Response<List<PeopleModel>> response) {
                if (response.body() != null){
                    Log.i("Step one", "Debug");
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(new GirlAdapter(response.body(), getActivity()));
                    sync(response.body());

                    Log.i("Step two", "Debug");
                } else {
                    Toast.makeText(getActivity(), "No se ha podido mostrar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PeopleModel>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                getFromDataBase();
            }
        });
    }


    private void sync(List<PeopleModel> peopleModels) {
        for(PeopleModel peopleModel : peopleModels) {
            store(peopleModel);
        }
    }

    private void store(PeopleModel peopleModelFromApi) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        PeopleModel peopleModel = realm.createObject(PeopleModel.class); // Create a new object

        peopleModel.setId(peopleModelFromApi.getId());
        peopleModel.setName(peopleModelFromApi.getName());
        peopleModel.setAge(peopleModelFromApi.getAge());
        peopleModel.setCareer(peopleModelFromApi.getCareer());
        peopleModel.setPhoto_url(peopleModelFromApi.getPhoto_url());

        realm.commitTransaction();
    }

    private void getFromDataBase() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<PeopleModel> query = realm.where(PeopleModel.class);

        RealmResults<PeopleModel> results = query.findAll();

        GirlAdapterFromDatabase girlAdapterFromDatabase = new GirlAdapterFromDatabase(results, getActivity());
        recyclerView.setAdapter(girlAdapterFromDatabase);
    }

}
