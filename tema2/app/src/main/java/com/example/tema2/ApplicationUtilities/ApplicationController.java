package com.example.tema2.ApplicationUtilities;

import android.app.Application;

import androidx.room.Room;

import com.example.tema2.DatabaseUtilities.AppDatabase;

public class ApplicationController extends Application {

    private static ApplicationController mInstance;
    private static AppDatabase mAppDatabase;

    public static ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mAppDatabase = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, Constants.DB_NAME)
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return mAppDatabase;
    }
}
