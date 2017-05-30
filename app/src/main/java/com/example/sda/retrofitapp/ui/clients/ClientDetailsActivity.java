package com.example.sda.retrofitapp.ui.clients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sda.retrofitapp.LoginActivity;
import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.model.ExternalKey;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        if (client != null) {
            logDebug(client.toString());
            clientName.setText(client.getName());
            clientCountry.setText(client.getCountry());
            clientPhoneNumber.setText(client.getPhoneNo());
            clientCity.setText(client.getCity());
        }

        apiService = new ApiClient().getApiService();

    }

    @OnClick(R.id.details_update)
    public void onSaveClick() {
        if (client == null) {
            addClient();
        } else {
            updateClient();
        }
    }

    private void readNewClient() {
        if (client == null) {
            client = new Client();
        }
        client.setName(clientName.getText().toString());
        client.setCity(clientCity.getText().toString());
        client.setCountry(clientCountry.getText().toString());
        client.setPhoneNo(clientPhoneNumber.getText().toString());
        logDebug(client.toString());
    }

    private void addClient() {
        readNewClient();

        client.setId(generateId());
        //client.setExternalKeys(new ArrayList<ExternalKey>());
        logDebug(client.toString());
        //Workaround
        List<Client> clientList = Arrays.asList(client);

        apiService.addClient(clientList).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == R.integer.WRONG_AUTHORIZATION_TOKEN) {
                        startLoginActivity();
                    }
                    logDebug("Successfully added client: " + client);
                    startClientsActivity();

                } else {
                    logDebug("Client creation was not successful");

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                makeLongToast("Error");
                logDebug("There was an error updating client: " + client);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (client != null) {
            getMenuInflater().inflate(R.menu.menu_client_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_client_detail_delete) {
            deleteClient();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteClient() {


        apiService.deleteClient(client).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == R.integer.WRONG_AUTHORIZATION_TOKEN) {
                        startLoginActivity();
                    }
                    logDebug("Successfully deleted client: " + client);
                    startClientsActivity();

                } else {
                    logDebug("Delete was  unsuccessful");

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                makeLongToast("Error");
                logDebug("There was an error updating client: " + client);
            }

        });
    }


    private void updateClient() {
        readNewClient();

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
                    logDebug("Update was unsuccessful");

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

    private int generateId() {
        return new Random(12).nextInt();
    }

}
