package com.saketkumar.perpule.ui.main;

import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.data.model.App;
import com.saketkumar.perpule.ui.base.BasePresenter;

import java.util.List;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private DataManager mResponseData;

    public MainPresenter(DataManager mResponseData) {
        this.mResponseData = mResponseData;
    }

    public List<App> getDataList() {
        return mResponseData.getDataList();
    }
}
