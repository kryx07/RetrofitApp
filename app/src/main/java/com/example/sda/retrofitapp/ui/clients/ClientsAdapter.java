package com.example.sda.retrofitapp.ui.clients;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.utils.MyApplicationProvider;

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
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplicationProvider.getApplication(),"Yo",Toast.LENGTH_LONG).show();
                Log.e("IsItWorking???","dupa");
            }
        });*/
        return new ClientsHolder(view);
    }

    @Override
    public void onBindViewHolder(final ClientsHolder holder, final int position) {
        holder.setClient(clientsList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 29.05.17 start new activity that shows client's details
                Toast.makeText(MyApplicationProvider.getApplication(), "Yo!", Toast.LENGTH_SHORT).show();
                Log.e("onClickListener", "onClick: " + holder.getAdapterPosition() + clientsList.get(holder.getAdapterPosition()).getName());
                //onClickSubject.onNext(element);
            }
        });
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