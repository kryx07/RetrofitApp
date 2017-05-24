package com.example.sda.retrofitapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by sda on 23.05.17.
 */

public class CallActivity {

    @SerializedName("PhoneNo")
    private String phoneNumber;
    @SerializedName("Incoming")
    private boolean incoming;
    @SerializedName("Start")
    private Date dateStart;
    @SerializedName("End")
    private Date dateEnd;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIncoming() {
        return incoming;
    }

    public void setIncoming(boolean incoming) {
        this.incoming = incoming;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
