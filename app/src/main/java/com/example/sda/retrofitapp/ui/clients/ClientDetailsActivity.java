package com.example.sda.retrofitapp.ui.clients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sda.retrofitapp.LoginActivity;
import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientDetailsActivity extends AppCompatActivity {

    @BindView(R.id.client_name_detail)
    EditText clientName;
    @BindView(R.id.client_city_detail)
    EditText clientCity;
    @BindView(R.id.client_country_detail)
    EditText clientCountry;
    @BindView(R.id.client_phone_number)
    EditText clientPhoneNumber;

    private ApiService apiService;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onBackPressed() {
        startClientsActivity();
    }

    private void startClientsActivity() {
        Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
        startActivity(intent);
        finish();
    }


    private void init() {
        Intent intent = getIntent();
        client = intent.getExtras().getParcelable(getString(R.string.client_parcelable_key));
        logDebug(client.toString());
        if (client != null) {
            clientName.setText(client.getName());
            clientCountry.setText(client.getCountry());
            clientPhoneNumber.setText(client.getPhoneNo());
            clientCity.setText(client.getCity());
        } else {
            finish();
        }

        apiService = new ApiClient().getApiService();

    }

    @OnClick(R.id.details_update)
    public void updateData() {

        Client updatedClient = client;
        updatedClient.setName(clientName.getText().toString());
        updatedClient.setCity(clientCity.getText().toString());
        updatedClient.setCountry(clientCountry.getText().toString());
        updatedClient.setPhoneNo(clientPhoneNumber.getText().toString());

        logDebug(client.toString());

        apiService.updateClient(client).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == R.integer.WRONG_AUTHORIZATION_TOKEN) {
                        startLoginActivity();
                    }
                    logDebug("Successfully updated client: " + client);
                    startClientsActivity();

                } else {
                    logDebug("Update was not successful");

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                makeLongToast("Error");
                logDebug("There was an error updating client: " + client);
            }

        });


    }

    private void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void logDebug(String string) {
        Log.e(getClass().getSimpleName(), string);

    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}