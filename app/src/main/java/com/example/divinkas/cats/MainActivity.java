package com.example.divinkas.cats;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Presenter.MainPresenter;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.View.ImainView;

public class MainActivity extends MvpAppCompatActivity implements ImainView {
    public static DefaultAPI_params defaultAPI_params = new DefaultAPI_params();

    @InjectPresenter
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isConnected(this)){ gotoErrView(isConnected(this)); }
        mainPresenter = new MainPresenter();
    }

    @Override
    public void checkAuthUser(boolean isValidUser) {
        Intent intent;
        if (isValidUser) { intent = new Intent(this, CatsActivity.class);
        } else { intent = new Intent(this, LoginActivity.class); }
        startActivity(intent);
        finish();
    }

    @Override
    public void checkConnect(boolean isConnect) {
        if(!isConnect){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void gotoErrView(boolean isConnected){
        if(!isConnected){
            Intent intent = new Intent(this, ErrActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm != null ? cm.getActiveNetworkInfo() : null;

        if (netinfo != null) {
            if (netinfo.isConnectedOrConnecting()) {
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
            } else
            return false;
        }
        return false;
    }

}
