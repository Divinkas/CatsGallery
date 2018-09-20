package com.example.divinkas.cats.Presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.View.ImainView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@InjectViewState
public class MainPresenter extends MvpPresenter<ImainView> {

    public MainPresenter(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        getViewState().checkConnect(true);
        getViewState().checkAuthUser(user != null);
    }

}
