package com.example.sda.retrofitapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import com.example.sda.retrofitapp.model.CallActivity;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wd42 on 25.05.17.
 */

public class MainActivity extends AppCompatActivity {

    private Button getActivitiesButton;
    private Button getClientsButton;
    private ApiService api;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        api = ApiClient.getService();

        getActivitiesButton = (Button) findViewById(R.id.get_activity_button);
        //recyclerListener = (AbsListView.RecyclerListener) findViewById(R.id.activity_list);
        getClientsButton = (Button) findViewById(R.id.get_clients_button);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        getActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivities();
            }
        });
        getClientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClients();
            }
        });

    }


    private void getActivities() {

        api.getActivities()
                .enqueue(new Callback<List<CallActivity>>() {
                    @Override
                    public void onResponse(Call<List<CallActivity>> call, Response<List<CallActivity>> response) {
                        if (response.isSuccessful()) {
                            List<CallActivity> callActivities = response.body();
                            Log.e("CallActivities: ", callActivities.toString());
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

    private void getClients() {

        api.getClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {
                    List<Client> callActivities = response.body();
                    Log.e("Clients: ", callActivities.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("Response Failure: ", "There was a failure getting activities", t);
                Log.e("Response Failure: ", t.getStackTrace().toString());
            }
        });


    }



}
