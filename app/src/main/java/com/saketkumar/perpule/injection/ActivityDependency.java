package com.saketkumar.perpule.injection;

import android.content.Context;

import com.saketkumar.perpule.MyApplication;
import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.ui.base.MvpPresenter;
import com.saketkumar.perpule.ui.base.MvpView;
import com.saketkumar.perpule.ui.main.MainActivity;
import com.saketkumar.perpule.ui.main.MainMvpPresenter;
import com.saketkumar.perpule.ui.main.MainMvpView;
import com.saketkumar.perpule.ui.main.MainPresenter;



public abstract class ActivityDependency<P extends MvpPresenter<? extends MvpView>> {

    public static MainActivityDependency inject(MainActivity activity) {
        return new MainActivityDependency(activity);
    }

    public abstract P getMvpPresenter();

    protected DataManager getDataManager(Context context) {
        return MyApplication.get(context.getApplicationContext()).getDataManager();
    }


    public static class MainActivityDependency extends ActivityDependency<MainMvpPresenter<MainMvpView>> {

        private MainMvpPresenter<MainMvpView> mvpPresenter;

        protected MainActivityDependency(MainActivity activity) {
            mvpPresenter = new MainPresenter<>(getDataManager(activity));
        }

        @Override
        public MainMvpPresenter<MainMvpView> getMvpPresenter() {
            return mvpPresenter;
        }
    }

}
