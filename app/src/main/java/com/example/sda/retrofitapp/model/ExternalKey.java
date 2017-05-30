package com.example.sda.retrofitapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ExternalKey implements Parcelable {

    @SerializedName("TableName")
    @Expose
    private String tableName;
    @SerializedName("RecId")
    @Expose
    private Integer recId;
    @SerializedName("ExternalSystem")
    @Expose
    private String externalSystem;
    @SerializedName("ExternalId")
    @Expose
    private String externalId;

    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getExternalSystem() {
        return externalSystem;
    }

    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExternalKey{" +
                "tableName='" + tableName + '\'' +
                ", recId=" + recId +
                ", externalSystem='" + externalSystem + '\'' +
                ", externalId='" + externalId + '\'' +
                ", id=" + id +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tableName);
        dest.writeValue(this.recId);
        dest.writeString(this.externalSystem);
        dest.writeString(this.externalId);
        dest.writeValue(this.id);
    }

    public ExternalKey() {
    }

    protected ExternalKey(Parcel in) {
        this.tableName = in.readString();
        this.recId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.externalSystem = in.readString();
        this.externalId = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<ExternalKey> CREATOR = new Creator<ExternalKey>() {
        @Override
        public ExternalKey createFromParcel(Parcel source) {
            return new ExternalKey(source);
        }

        @Override
        public ExternalKey[] newArray(int size) {
            return new ExternalKey[size];
        }
    };
}