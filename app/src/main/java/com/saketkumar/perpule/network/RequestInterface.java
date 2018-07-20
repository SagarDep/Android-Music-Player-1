package com.saketkumar.perpule.network;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("bins/mxcsl/")
    Observable<ResponseData> register();
}
