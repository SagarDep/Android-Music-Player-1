package com.saketkumar.perpule;

import android.app.Application;
import android.content.Context;

import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.injection.ApplicationDependency;


public class MyApplication extends Application {

    private ApplicationDependency applicationDependency;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationDependency = ApplicationDependency.inject(this);
    }

    public DataManager getDataManager() {
        return applicationDependency.getDataManager();
    }
}
