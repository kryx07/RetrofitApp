package com.example.sda.retrofitapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Client implements Parcelable{

    @SerializedName("Name")
    private String name;
    @SerializedName("City")
    private String city;
    @SerializedName("Country")
    private String country;
    @SerializedName("PhoneNo1")
    private String phoneNo;
    @SerializedName("Id")
    private int id;
    @SerializedName("ExternalKeys")
    private List<ExternalKey> externalKeys;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.phoneNo);
        dest.writeInt(this.id);
        dest.writeTypedList(this.externalKeys);
    }

    public Client() {
    }

    protected Client(Parcel in) {
        this.name = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.phoneNo = in.readString();
        this.id = in.readInt();
        this.externalKeys = in.createTypedArrayList(ExternalKey.CREATOR);
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", id=" + id +
                '}';
    }



}
