package com.example.sda.retrofitapp.ui.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ClientsHolder> {


    public interface ClientClickListener {
        /**
         * Called when an item is clicked.
         *
         * @param client Client to be passed .
         */
        void onClientClick(Contact client);
    }

    public ContactsAdapter(ClientClickListener clientClickListener) {
        this.clientClickListener = clientClickListener;
    }

    private ClientClickListener clientClickListener;

    private List<Contact> clientsList = new ArrayList<>();

    public void setData(List<Contact> clientList) {
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
    public void onBindViewHolder(final ClientsHolder holder, final int position) {
        holder.setContact(clientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return clientsList.size();
    }

    class ClientsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.client_name)
        TextView clientName;

        private Contact contact;

        ClientsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clientClickListener.onClientClick(contact);
                }
            });
        }


        public void setContact(Contact contact) {
            this.contact = contact;
            clientName.setText(contact.getLastName());
        }

    }


}