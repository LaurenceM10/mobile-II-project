package com.apps.skadied.girlshub.ui.activities;

import android.app.ProgressDialog;
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
import com.apps.skadied.girlshub.models.AccessTokenModel;
import com.apps.skadied.girlshub.models.CreateClient;
import com.apps.skadied.girlshub.models.ClientModel;
import com.tumblr.remember.Remember;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText email;
    private Button signup;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
        initActions();
    }

    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        signup = findViewById(R.id.signup);
    }

    private void initActions() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppCompatAlertDialogStyle);
                    progressDialog.setMessage(getString(R.string.signup_loading));
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    fetchHttpRequest();
                }
            }
        });
    }


    private void fetchHttpRequest(){
        CreateClient clientModel = new CreateClient();
        clientModel.setUsername(username.getText().toString());
        clientModel.setEmail(email.getText().toString());
        clientModel.setPassword(password.getText().toString());

        Call<CreateClient> call = Api.instance().createClient(clientModel);
        call.enqueue(new Callback<CreateClient>() {
            @Override
            public void onResponse(@NonNull Call<CreateClient> call, @NonNull Response<CreateClient> response) {
                if (response.body() != null){
                    //If the account is created, do login
                    doLogin(username.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(SignupActivity.this, "Error al crear su cuenta", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CreateClient> call, @NonNull Throwable t) {
                Toast.makeText(SignupActivity.this, "Error al crear su cuenta", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void doLogin(String username, String password){
        ClientModel user = new ClientModel();

        Call<AccessTokenModel> call = Api.instance().login(user);
        call.enqueue(new Callback<AccessTokenModel>() {
            @Override
            public void onResponse(@NonNull Call<AccessTokenModel> call, @NonNull Response<AccessTokenModel> response) {
                if (response.body() != null) {
                    Remember.putString("user", response.body().getUserId());
                    startActivity(new Intent(SignupActivity.this, Main2Activity.class));
                    progressDialog.dismiss();
                    finish();
                } else {
                    Log.i("Debug", " Null Response");
                    Toast.makeText(getApplicationContext(), "Por favor ingrese sus datos correctamente",
                            Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessTokenModel> call, @NonNull Throwable t) {
                Log.i("Debug", "Error");
                progressDialog.dismiss();
            }
        });
    }

}
