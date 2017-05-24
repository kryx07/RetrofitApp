package com.example.sda.retrofitapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sda.retrofitapp.R;

/**
 * Created by wd42 on 24.05.17.
 */

public class SharedPreferencesManager {

    private Activity mainActivity;

    public SharedPreferencesManager(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void writeAccessToken(String string) {

        SharedPreferences sharedPreferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mainActivity.getString(R.string.saved_access_token), string);
        editor.apply();

        String savedAccessToken =
                sharedPreferences.getString(mainActivity.getString(R.string.saved_access_token),
                                           mainActivity.getString(R.string.no_token));
        Log.e("Saved access token", savedAccessToken);
    }

    public String readAccessToken() {
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        String defVal = mainActivity.getString(R.string.no_token);
        return sharedPref.getString(mainActivity.getString(R.string.saved_access_token), defVal);

    }
}
