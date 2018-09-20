package com.example.divinkas.cats.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.MainActivity;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.View.IsettingView;

@InjectViewState
public class SettingPresenter extends MvpPresenter<IsettingView> {

    public SettingPresenter() {
        getViewState().findElements();
        getViewState().init();
    }

    public void setDefaultTextQuery(String s){
        MainActivity.defaultAPI_params.q = s;
    }

}
