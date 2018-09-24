package com.example.divinkas.cats;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Presenter.MainPresenter;
import com.example.divinkas.cats.View.ImainView;

public class MainActivity extends MvpAppCompatActivity implements ImainView {

    @InjectPresenter
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mainPresenter.isNetworkConnect(this)){
            if(mainPresenter.isAuthorizeUser()){ gotoNextView("Cats"); }
            else { gotoNextView("Registration"); }
        }
        else {
            gotoNextView("Error");
        }
    }

    @Override
    public void gotoNextView(String nameNextRenderView) {
        Intent intent = mainPresenter.getViewIntentByName(nameNextRenderView);
        startActivity(intent);
        finish();
    }


}
