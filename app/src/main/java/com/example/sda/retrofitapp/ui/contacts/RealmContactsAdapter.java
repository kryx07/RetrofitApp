package com.example.sda.retrofitapp.ui.contacts;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


public class RealmContactsAdapter extends RealmRecyclerViewAdapter<Contact, RealmContactsAdapter.ClientsHolder> {


    public RealmContactsAdapter(@Nullable OrderedRealmCollection<Contact> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public ClientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_client, parent, false);

        return new ClientsHolder(view);
    }

    @Override
    public void onBindViewHolder(final ClientsHolder holder, final int position) {
        OrderedRealmCollection<Contact> data = getData();
        if (data != null) {
            holder.setContact(data.get(position));
        }
    }

    class ClientsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.client_name)
        TextView clientName;

        ClientsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setContact(Contact contact) {
            clientName.setText(contact.getLastName());
        }

    }


}