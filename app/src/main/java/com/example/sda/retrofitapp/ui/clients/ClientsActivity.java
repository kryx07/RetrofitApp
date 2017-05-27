package com.example.sda.retrofitapp.ui.clients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.CallActivity;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wd42 on 25.05.17.
 */

public class ClientsActivity extends AppCompatActivity {

    @BindView(R.id.clients_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar spinner;

    private ApiService apiService;
    private ApiClient apiClient;
    private SharedPreferencesManager sharedPreferencesManager;
    private ClientsAdapter clientsAdapter;
    private CallActivitiesAdapter callActivitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();


    }

    private void init() {
        sharedPreferencesManager = new SharedPreferencesManager();
        apiClient = new ApiClient(sharedPreferencesManager);
        apiService = apiClient.getApiService();

        callActivitiesAdapter = new CallActivitiesAdapter();
        clientsAdapter = new ClientsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(clientsAdapter);


    }


    @OnClick(R.id.get_activity_button)
    void getActivities() {

        showSpinner();

        apiService.getActivities()
                .enqueue(new Callback<List<CallActivity>>() {
                    @Override
                    public void onResponse(Call<List<CallActivity>> call, Response<List<CallActivity>> response) {
                        if (response.isSuccessful()) {
                            List<CallActivity> callActivities = response.body();
                            Log.e("CallActivities: ", callActivities.toString());

                            setRecyclerAdapter(callActivitiesAdapter);
                            callActivitiesAdapter.setData(callActivities);

                            hideSpinner();
                        } else {
                            Log.e("Response unsuccessful: ", "There was a problem with your getting activities");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<CallActivity>> call, Throwable t) {
                        Log.e("Response Failure: ", "There was a failure getting activities", t);
                        Log.e("Response Failure: ", t.getStackTrace().toString());
                    }
                });

    }

    @OnClick(R.id.get_clients_button)
    void getClients() {

        showSpinner();

        apiService.getClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {
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
                Log.e("Response Failure: ", t.getStackTrace().toString());
            }
        });


    }

    private void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    private void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    private void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }


}