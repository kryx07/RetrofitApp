package com.example.sda.retrofitapp.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sda.retrofitapp.LoginActivity;
import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Contact;
import com.example.sda.retrofitapp.network.ApiClient;
import com.example.sda.retrofitapp.network.ApiService;
import com.example.sda.retrofitapp.ui.clients.ClientDetailsActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsActivity extends AppCompatActivity implements ContactsAdapter.ClientClickListener {

    @BindView(R.id.clients_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ApiService apiService;
    private RealmRecyclerViewAdapter<Contact, RealmContactsAdapter.ClientsHolder> contactsAdapter;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContacts();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startLoginActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clients, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_clients_add) {


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClientClick(Contact client) {
        // TODO: 29.05.17 open new activity
        startClientDetailsActivity(client);
    }


    private void init() {
        realm = Realm.getDefaultInstance();

        apiService = new ApiClient().getApiService();

        contactsAdapter = new RealmContactsAdapter(realm.where(Contact.class).beginsWith("lastName", "Info").findAll(), true);
        recyclerView.setAdapter(contactsAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getContacts();
    }

    void getContacts() {


        showSpinner();

        //android.os.SystemClock.sleep(1500);

        apiService.getContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == R.integer.WRONG_AUTHORIZATION_TOKEN) {
                        startLoginActivity();
                    }

                    final List<Contact> contactList = response.body();
                    logDebug("Contact list: " + contactList.toString());

                    setRecyclerAdapter(contactsAdapter);

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for (Contact contact : contactList) {
                                realm.copyToRealmOrUpdate(contact);
                            }
                            logDebug(realm.where(Contact.class).findAll().toString());

                        }
                    });


                    hideSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                logDebug("Response Failure: " + "There was a failure getting activities");
                logDebug("Response Failure: " + Arrays.toString(t.getStackTrace()));
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

    private void startClientDetailsActivity(@Nullable Contact client) {
        Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
        //intent.putExtra(getString(R.string.client_parcelable_key), client);
        startActivity(intent);
    }

    private void logDebug(String string) {
        Log.e(getClass().getSimpleName(), string);

    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
