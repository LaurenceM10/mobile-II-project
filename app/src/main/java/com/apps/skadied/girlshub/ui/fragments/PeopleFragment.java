package com.apps.skadied.girlshub.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.PeopleModel;
import com.apps.skadied.girlshub.ui.adapters.GirlAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public PeopleFragment() {
        // Required empty public constructor
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
        fetchGirls();
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
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(new GirlAdapter(response.body(), getActivity()));
                } else {
                    Toast.makeText(getActivity(), "No se ha podido mostrar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PeopleModel>> call, @NonNull Throwable t) {

            }
        });
    }

}
