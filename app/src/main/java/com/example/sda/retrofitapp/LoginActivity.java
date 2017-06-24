package com.example.sda.retrofitapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sda.retrofitapp.model.LoginResponse;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.ui.contacts.ContactsActivity;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_text)
    EditText editMail;
    @BindView(R.id.password_text)
    EditText editPassword;
    @BindView(R.id.submit_button)
    Button submitButton;

    private ApiService apiService;

    private SharedPreferencesManager sharedPreferencesManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @OnClick(R.id.submit_button)
    public void onSubmitClick() {
        login(editMail.getText().toString(), editPassword.getText().toString());

        /*Realm realm = Realm.getDefaultInstance();
        apiService.getContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    List<Contact> contactList = response.body();
                    logDebug(contactList.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });*/
    }

    private void init() {
        sharedPreferencesManager = new SharedPreferencesManager();
        apiService = new ApiClient().getApiService();
    }

    private void login(String email, String password) {

        progressDialog = ProgressDialog.show(this, "Login", "Logging In....");

        apiService.login(email, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            String accessToken = response.body().getAccessToken();
                            Log.e("Access token", accessToken);
                            sharedPreferencesManager.writeAccessToken(accessToken);
                            Log.e("Read access token", sharedPreferencesManager.readAccessToken());

                            progressDialog.hide();

                            //start new activity
                            startMainActivity();

                        } else {
                            logDebug("Login response not successful");
                            makeLongToast(getString(R.string.toast_request_error));
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        // TODO: 24.05.17 Handle failure
                        logDebug("Login Error: something's wrong with logging in");
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_login_error), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), ContactsActivity.class);
        //intent.putExtra("dupa");
        startActivity(intent);
        //finish();
    }


    private void logDebug(String string) {
        Log.e(getClass().getSimpleName(), string);

    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
