package com.example.sda.retrofitapp.ui.clients;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sda.retrofitapp.R;
import com.example.sda.retrofitapp.model.CallActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JUNED on 6/10/2016.
 */
public class CallActivitiesAdapter extends RecyclerView.Adapter<CallActivitiesAdapter.CallActivitiesHolder> {


    private List<CallActivity> callActivityList = new ArrayList<>();

    public void setData(List<CallActivity> clientList) {
        this.callActivityList.clear();
        this.callActivityList.addAll(clientList);
        notifyDataSetChanged();
    }

    @Override
    public CallActivitiesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_call_activity, parent, false);
        return new CallActivitiesHolder(view);
    }

    @Override
    public void onBindViewHolder(CallActivitiesHolder holder, int position) {
        holder.setClient(callActivityList.get(position));
    }

    @Override
    public int getItemCount() {
        return callActivityList.size();
    }

    class CallActivitiesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.phone_number)
        TextView phoneNumber;

        public CallActivitiesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setClient(CallActivity client) {
            phoneNumber.setText(client.getPhoneNo());
        }

    }


}