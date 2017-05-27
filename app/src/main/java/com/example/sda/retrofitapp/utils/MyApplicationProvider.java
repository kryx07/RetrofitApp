package com.example.sda.retrofitapp.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MyApplicationProvider extends Application {

    /*public void onCreate() {
        super.onCreate();
        MyApplicationProvider.context = getApplicationContext();
    }

    private static Context context;

    public static Context getAppContext() {
        return MyApplicationProvider.context;
    }*/


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

    private static void logError(Exception e) {
        Log.e("MyApplicationProvider: ", Arrays.toString(e.getStackTrace()));
    }
}