package com.apps.skadied.girlshub.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.PersonModel;
import com.apps.skadied.girlshub.ui.activities.EditActivity;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by skadied on 05-07-18.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    List<PersonModel> people;
    private Context context;

    public PersonAdapter(List<PersonModel> people, Context context) {
        this.people = people;
        this.context = context;

        sortEvents();
    }

    private void sortEvents() {
        Collections.reverse(people);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PersonModel person = people.get(position);

        holder.name.setText(person.getName());
        holder.photo_url.setText(person.getPhoto_url());
        holder.age.setText(person.getAge());
        holder.phone.setText(person.getPhone());
        holder.address.setText(person.getAddress());

        //To Update i guess
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PersonModel> call = Api.instance().deletePerson(person.getId());
                call.enqueue(new Callback<PersonModel>() {
                    @Override
                    public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                        Toast.makeText(context, "Se ha eliminado con éxito", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PersonModel> call, Throwable t) {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", person.getId());
                context.startActivity(intent);

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView photo_url;
        TextView age;
        TextView phone;
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            photo_url = itemView.findViewById(R.id.photo_url);
            age = itemView.findViewById(R.id.age);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
        }
    }

}