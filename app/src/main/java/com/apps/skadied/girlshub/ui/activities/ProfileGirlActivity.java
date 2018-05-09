package com.apps.skadied.girlshub.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.apps.skadied.girlshub.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class ProfileGirlActivity extends AppCompatActivity {
    private Intent intent;
    private SimpleDraweeView simpleDraweeView;
    private TextView username;
    private TextView career;
    private TextView age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_girl);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set arrow back icon in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        initViews();
        getIntentExtras();
        setIntentData();
    }

    /**
     * To get references of the view elements
     */
    private void initViews(){
        simpleDraweeView = findViewById(R.id.picture);
        username = findViewById(R.id.username);
        career = findViewById(R.id.career);
        age = findViewById(R.id.age);
    }

    /**
     * Set data from intent
     */
    private void setIntentData(){
        simpleDraweeView.setImageURI(intent.getStringExtra("url"));
        username.setText(intent.getStringExtra("name"));
        age.setText(intent.getStringExtra("age").concat(" años"));
        career.setText("Carrera: " + intent.getStringExtra("career"));
    }

    /**
     * To get data from the intent
     */
    private void getIntentExtras() {
        intent = getIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
