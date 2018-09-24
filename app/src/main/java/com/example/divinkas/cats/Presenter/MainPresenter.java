package com.example.divinkas.cats.Presenter;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.Model.MainModel;
import com.example.divinkas.cats.View.ImainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<ImainView> {

    private MainModel mainModel;

    public MainPresenter(){
    }

    public boolean isNetworkConnect(Context context){
        if(mainModel == null){ mainModel = new MainModel(context); }
        return mainModel.isNetworkState();
    }

    public boolean isAuthorizeUser(){
        return mainModel.isAuthorizeUser();
    }

    public Intent getViewIntentByName(String name){
        return mainModel.getViewIntentByName(name);
    }
}
