package com.example.sda.retrofitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sda.retrofitapp.model.LoginResponse;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editMail;
    private EditText editPassword;
    private Button submitButton;

    private ApiService apiService;
    private ApiClient apiClient;

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void init() {
        sharedPreferencesManager = new SharedPreferencesManager();
        apiClient = new ApiClient(sharedPreferencesManager);
        apiService = apiClient.getApiService();


        editMail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);
        submitButton = (Button) findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editMail.getText().toString(), editPassword.getText().toString());
            }
        });
    }

    private void login(String email, String password) {

        apiService.login(email, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            String accessToken = response.body().getAccessToken();
                            Log.e("Access token", accessToken);
                            sharedPreferencesManager.writeAccessToken(accessToken);
                            Log.e("Read access token", sharedPreferencesManager.readAccessToken());

                            //start new activity
                            openMainActivity();

                        } else {
                            Log.e("Access token", "Login error");
                            Toast.makeText(getApplicationContext(), getString(R.string.toast_request_error), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        // TODO: 24.05.17 Handle failure
                        Log.e("Login Error", "Something's wrong with loging in!");
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_login_error), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.putExtra("dupa");
        startActivity(intent);
        finish();
    }


}
