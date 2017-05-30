package com.example.sda.retrofitapp.ui.clients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sda.retrofitapp.LoginActivity;
import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.CallActivity;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsActivity extends AppCompatActivity implements ClientsAdapter.ClientClickListener {

    @BindView(R.id.clients_recycler)
    RecyclerView recyclerView;
    /*@BindView(R.id.pbHeaderProgress)
    ProgressBar spinner;*/
    @BindView(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ApiService apiService;
    private ClientsAdapter clientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getClients();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startLoginActivity();
    }


    @Override
    public void onClientClick(Client client) {
        // TODO: 29.05.17 open new activity
       // Toast.makeText(getApplicationContext(), "isItWorking???" + client.getName(), Toast.LENGTH_LONG).show();
        startClientDetailsActivity(client);
    }


    private void init() {
        apiService = new ApiClient().getApiService();

        clientsAdapter = new ClientsAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(clientsAdapter);

        getClients();
    }

    void getClients() {


        showSpinner();

        //android.os.SystemClock.sleep(1500);

        apiService.getClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == R.integer.WRONG_AUTHORIZATION_TOKEN) {
                        startLoginActivity();
                    }

                    List<Client> clientsList = response.body();
                    Log.e("Clients: ", clientsList.toString());

                    setRecyclerAdapter(clientsAdapter);
                    clientsAdapter.setData(clientsList);

                    hideSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("Response Failure: ", "There was a failure getting activities", t);
                Log.e("Response Failure: ", Arrays.toString(t.getStackTrace()));
            }
        });
    }

    private void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    private void showSpinner() {
        swipeRefreshLayout.setRefreshing(true);
    }

    private void hideSpinner() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void startClientDetailsActivity(Client client) {
        Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
        intent.putExtra(getString(R.string.client_parcelable_key), client);
        startActivity(intent);
    }


}
