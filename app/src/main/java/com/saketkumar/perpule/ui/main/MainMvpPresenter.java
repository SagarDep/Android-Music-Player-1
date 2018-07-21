package com.saketkumar.perpule.ui.main;


import com.saketkumar.perpule.data.model.App;
import com.saketkumar.perpule.ui.base.MvpPresenter;

import java.util.List;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    List<App> getDataList();

}
