package com.example.sda.retrofitapp.ui.clients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

    private ApiService apiService;
    private ApiClient apiClient;
    private SharedPreferencesManager sharedPreferencesManager;
    private ClientsAdapter adapter;

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

        adapter = new ClientsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);



    }
/*

    @OnClick(R.id.get_activity_button)
     void getActivities() {

        apiService.getActivities()
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
*/

    @OnClick(R.id.get_clients_button)
    void getClients() {

        apiService.getClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {
                    List<Client> clientsList = response.body();
                    Log.e("Clients: ", clientsList.toString());

                    adapter.setData(clientsList);
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
