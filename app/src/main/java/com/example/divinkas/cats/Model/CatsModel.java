package com.example.divinkas.cats.Model;

import com.example.divinkas.cats.Adapter.GalleryAdapter;
import com.example.divinkas.cats.Data.Hit;
import com.example.divinkas.cats.Data.PicktureObj;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.Retrofit.IPickturesList;
import com.example.divinkas.cats.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CatsModel {

    private Retrofit retrofit;
    private IPickturesList iPickturesList;
    private Observer<PicktureObj> objObserver;

    private List<Hit> list = new ArrayList<>();
    private PicktureObj data = new PicktureObj();

    public CatsModel() {
        retrofit= RetrofitClient.getInstance();
        iPickturesList = retrofit.create(IPickturesList.class);
    }

    public void updateData(GalleryAdapter galleryAdapter) {

         objObserver= new Observer<PicktureObj>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(PicktureObj picktureObj) {
                data = picktureObj;
                list = data.getHits();
                galleryAdapter.setNewListData(list);
            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        iPickturesList.getListPicktures(DefaultAPI_params.key, DefaultAPI_params.q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribe(objObserver);

    }
}
