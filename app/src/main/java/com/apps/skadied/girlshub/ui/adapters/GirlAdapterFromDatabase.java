package com.apps.skadied.girlshub.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.models.PeopleModel;
import com.apps.skadied.girlshub.ui.activities.ProfileGirlActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Lauren Steven on 9/5/2018.
 */
public class GirlAdapterFromDatabase extends RecyclerView.Adapter<GirlAdapterFromDatabase.ViewHolder> {
    private RealmResults<PeopleModel> girls;
    private Context context;

    public GirlAdapterFromDatabase(RealmResults<PeopleModel> girls, Context context) {
        this.girls = girls;
        this.context = context;
    }

    @Override
    public GirlAdapterFromDatabase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.girl_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlAdapterFromDatabase.ViewHolder holder, int position) {
        final PeopleModel peopleModel = girls.get(position);

        holder.itemGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileGirlActivity.class);
                intent.putExtra("name", peopleModel.getName());
                intent.putExtra("career", peopleModel.getCareer());
                intent.putExtra("url", peopleModel.getPhoto_url());
                intent.putExtra("age", peopleModel.getAge());
                context.startActivity(intent);
            }
        });

        holder.picture.setImageURI(peopleModel.getPhoto_url());
        holder.username.setText(peopleModel.getName());
        holder.age.setText(peopleModel.getAge().concat(" años"));
        holder.career.setText(peopleModel.getCareer());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemGirl;
        SimpleDraweeView picture;
        TextView username;
        TextView age;
        TextView career;

        public ViewHolder(View itemView) {
            super(itemView);
            itemGirl = itemView.findViewById(R.id.item_girl);
            picture = itemView.findViewById(R.id.picture);
            username = itemView.findViewById(R.id.username);
            age = itemView.findViewById(R.id.age);
            career = itemView.findViewById(R.id.career);
        }
    }
}
