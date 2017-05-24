package com.example.sda.retrofitapp.model.activity;

import android.app.Activity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wd42 on 24.05.17.
 */

public class Activities {

    @SerializedName("Activities")
    private List<ModelActivity> activities;

    public List<ModelActivity> getActivities() {
        return activities;
    }

    public Activities(List<ModelActivity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "activities=" + activities +
                '}';
    }
}
