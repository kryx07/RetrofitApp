package com.example.sda.retrofitapp;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.InvocationTargetException;

public class MyApplication extends Application {

    /*public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    private static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }*/


    public static Application getApplicationUsingReflection()  {
        try {
            return (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication").invoke(null, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}