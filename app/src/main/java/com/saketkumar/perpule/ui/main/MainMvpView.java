package com.saketkumar.perpule.ui.main;

import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.ui.base.MvpView;


public interface MainMvpView extends MvpView {

    void handleResponse(DataManager responseData);

}
