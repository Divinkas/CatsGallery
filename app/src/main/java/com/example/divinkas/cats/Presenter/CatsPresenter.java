package com.example.divinkas.cats.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.Data.PicktureObj;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.Retrofit.IPickturesList;
import com.example.divinkas.cats.Retrofit.RetrofitClient;
import com.example.divinkas.cats.View.IcatsView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@InjectViewState
public class CatsPresenter extends MvpPresenter<IcatsView> {
    private PicktureObj data;

    public CatsPresenter(){
        getViewState().findElementsView();
        Retrofit retrofit = RetrofitClient.getInstance();
        IPickturesList iPickturesList = retrofit.create(IPickturesList.class);
        Observer<PicktureObj> objObserver = new Observer<PicktureObj>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PicktureObj picktureObj) {
                data = picktureObj;
                getViewState().showListImages(data);
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
                .subscribe(objObserver);
    }

}
