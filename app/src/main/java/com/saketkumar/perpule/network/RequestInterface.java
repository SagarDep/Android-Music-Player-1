package com.saketkumar.perpule.network;

import com.saketkumar.perpule.data.DataManager;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("bins/mxcsl/")
    Observable<DataManager> register();
}
