package com.saketkumar.perpule.ui;

import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.data.model.App;
import com.saketkumar.perpule.ui.base.MvpView;
import com.saketkumar.perpule.ui.main.MainActivity;
import com.saketkumar.perpule.ui.main.MainMvpPresenter;
import com.saketkumar.perpule.ui.main.MainMvpView;
import com.saketkumar.perpule.utils.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainMvpPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxSchedulersOverrideRule = new
            RxSchedulersOverrideRule();
    @Mock
    DataManager dataManager;

    @Mock
    MainMvpView mainMvpView;

    private MainActivity mainActivity;

    private MainMvpPresenter mainMvpPresenter;

    @Before
    public void setUp() {

        mainMvpPresenter = new MainMvpPresenter() {
            @Override
            public List<App> getDataList() {
                return null;
            }

            @Override
            public void attachView(MvpView mvpView) {

            }

            @Override
            public void detachView() {

            }
        };
        mainMvpPresenter.attachView(mainMvpView);
    }

    @After
    public void tearDown() {
        mainMvpPresenter.detachView();
    }

    @Test
    public void loadAllData_validDataList_ReturnsResults() {

        when(dataManager.getDataList());

        mainMvpPresenter.getDataList();

        verify(mainMvpView).showLoading();
        verify(mainMvpView, never()).showLoading();
        verify(mainMvpView, never()).showLoading();

    }

    @Test
    public void loadAllData_EmptyDataList_ReturnsNoDataResults() {

        MainActivity mainActivity = new MainActivity();
        mainActivity.handleResponse(new DataManager());

        when(dataManager.getDataList());

        mainMvpPresenter.getDataList();

        verify(mainMvpView, never());
        verify(mainMvpView);
        verify(mainMvpView, never());

    }


}
