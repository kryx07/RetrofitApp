package com.example.sda.retrofitapp.ui.clients;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Client;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JUNED on 6/10/2016.
 */
public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsHolder> {


    private List<Client> clientsList = new ArrayList<>();

    public void setData(List<Client> clientList) {
        this.clientsList.clear();
        this.clientsList.addAll(clientList);
        notifyDataSetChanged();
    }

    @Override
    public ClientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_client, parent, false);
        return new ClientsHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientsHolder holder, int position) {
        holder.setClient(clientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return clientsList.size();
    }

    class ClientsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.client_name)
        TextView clientName;

        public ClientsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setClient(Client client) {
            clientName.setText(client.getName());
        }

    }


}