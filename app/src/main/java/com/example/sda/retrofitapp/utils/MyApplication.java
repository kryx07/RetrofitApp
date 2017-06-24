package com.example.sda.retrofitapp.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    private static MyApplication myApplication;

    /*public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    private static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).setModules(new SimpleRealmModule()).build();

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Application getApplication() {
        try {
            return (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication").invoke(null, (Object[]) null);
        } catch (IllegalAccessException e) {
            logError(e);
        } catch (InvocationTargetException e) {
            logError(e);
        } catch (NoSuchMethodException e) {
            logError(e);
        } catch (ClassNotFoundException e) {
            logError(e);
        }

        return null;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    private static void logError(Exception e) {
        Log.e("MyApplication: ", Arrays.toString(e.getStackTrace()));
    }
}