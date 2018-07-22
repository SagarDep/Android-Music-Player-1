package com.saketkumar.perpule.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.saketkumar.perpule.R;
import com.saketkumar.perpule.data.model.App;
import com.saketkumar.perpule.injection.ActivityDependency;
import com.saketkumar.perpule.ui.base.BaseActivity;
import com.saketkumar.perpule.ui.main.adapter.DataAdapter;
import com.saketkumar.perpule.network.RequestInterface;
import com.saketkumar.perpule.data.DataManager;
import com.saketkumar.perpule.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements MainMvpView {

    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private MainMvpPresenter<MainMvpView> mMainMvpPresenter;

    private DataAdapter mAdapter;

    private ArrayList<App> mAndroidArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityDependency.MainActivityDependency dependency = ActivityDependency.inject(this);
        mMainMvpPresenter = dependency.getMvpPresenter();
        mMainMvpPresenter.attachView(this);

        mCompositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadJSON();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void loadJSON() {
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);

        mCompositeDisposable.add(requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    public void handleResponse(DataManager responseData) {
        if( responseData != null){
            List<App> dataList = responseData.getDataList();
            mAndroidArrayList = new ArrayList<>();
            if(dataList!= null){
                mAndroidArrayList.addAll(dataList);
            }
            mAdapter = new DataAdapter(mAndroidArrayList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mMainMvpPresenter.detachView();
    }

}
