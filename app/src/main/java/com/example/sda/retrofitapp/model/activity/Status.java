package com.example.sda.retrofitapp.model.activity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wd42 on 24.05.17.
 */

class Status {

    @SerializedName("Name")
    private String name;
    @SerializedName("Value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
