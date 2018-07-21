package com.saketkumar.perpule.injection;

import android.app.Application;
import android.content.Context;

import com.saketkumar.perpule.data.DataManager;

public class ApplicationDependency {

    private DataManager mDataManager;

    private ApplicationDependency(Context context) {
        DataManager mDataManager = new DataManager();
    }

    public static ApplicationDependency inject(Application application) {
        return new ApplicationDependency(application);
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
