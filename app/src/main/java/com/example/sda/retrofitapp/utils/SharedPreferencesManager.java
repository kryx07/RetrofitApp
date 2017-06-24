package com.example.sda.retrofitapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sda.retrofitapp.R;

/**
 * Created by wd42 on 24.05.17.
 */

public class SharedPreferencesManager {

    private Context context;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager() {
        this.context = MyApplication.getApplication();
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        editor = context.getSharedPreferences(context.getString(R.string.shared_prefs), 0).edit();
    }

    public void writeAccessToken(String string) {

        editor.putString(context.getString(R.string.shared_prefs), string);
        editor.apply();

        String savedAccessToken =
                sharedPreferences.getString(context.getString(R.string.shared_prefs),
                        context.getString(R.string.no_token));
        Log.e("Saved access token", savedAccessToken);
    }

    public String readAccessToken() {
        String defVal = context.getString(R.string.no_token);
        return sharedPreferences.getString(context.getString(R.string.shared_prefs), defVal);

    }
}
