package com.apps.skadied.girlshub.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.skadied.girlshub.R;
import com.apps.skadied.girlshub.api.Api;
import com.apps.skadied.girlshub.models.UserModel;
import com.apps.skadied.girlshub.models.AccessTokenModel;
import com.tumblr.remember.Remember;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by skadied on 05-08-18.
 */

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Remember.init(getApplicationContext(), "com.apps.skadied.girlshub");
        setContentView(R.layout.login);
        initViews();
        initActions();
    }

    private void initViews() {
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
    }

    private void initActions() {
        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fetchHttpRequest();
        }
    });
    }

    public void fetchHttpRequest(){
        UserModel user = new UserModel();

        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        Call<AccessTokenModel> call = Api.instance().login(user);
        call.enqueue(new Callback<AccessTokenModel>() {
            @Override
            public void onResponse(@NonNull Call<AccessTokenModel> call, @NonNull Response<AccessTokenModel> response) {
                if (response.body() != null) {
                    Remember.putString("user", response.body().getUserId());
                    finish();
                } else {
                    Log.i("Debug", " Null Response");
                    Toast.makeText(getApplicationContext(), "Por favor ingrese sus datos correctamente",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessTokenModel> call, @NonNull Throwable t) {
                Log.i("Debug", "Error");
            }
        });
    }

    public void authenticateLogin(View view) {
        if ((username.getText().toString() == null) ||
        (password.getText().toString() == null)) {
            Toast.makeText(getApplicationContext(), "Por favor rellene los campos solicitados",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
