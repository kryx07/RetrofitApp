package com.example.sda.retrofitapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sda.retrofitapp.model.LoginResponse;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editMail;
    private EditText editPassword;
    private Button button;
    private ApiService api;

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void init() {
        sharedPreferencesManager = new SharedPreferencesManager(this);
        api = ApiClient.getApiClient();

        editMail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editMail.getText().toString(), editPassword.getText().toString());
            }
        });
    }

    /*private void getCalls() {
        api.getActivities().enqueue(new Callback<List<CallActivity>>() {
            @Override
            public void onResponse(Call<List<CallActivity>> call, Response<List<CallActivity>> response) {
                if (response.isSuccessful()) {
                    List<CallActivity> activities = response.body();
                } else {
                    // TODO: 23.05.17 Handle error
                }
            }

            @Override
            public void onFailure(Call<List<CallActivity>> call, Throwable t) {
                // TODO: 23.05.17 Handle failure: connection
            }
        });
    }*/

    private void login(String email, String password) {
        api.login(email, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            String accessToken = response.body().getAccessToken();
                            Log.e("Access token", accessToken);
                            sharedPreferencesManager.writeAccessToken(accessToken);
                            Log.e("Read access token", sharedPreferencesManager.readAccessToken());

                        } else {
                            Log.e("Access token", "Login error");
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        // TODO: 24.05.17 Handle failure
                        Log.e("Login Error", "Something's wrong with loging in!");
                    }
                });
    }

}
