package com.example.divinkas.cats.Retrofit;

import com.example.divinkas.cats.Data.PicktureObj;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPickturesList {

    @GET("api/")
    Observable<PicktureObj> getListPicktures(@Query("key") String key, @Query("q") String q);

}
