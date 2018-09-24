package com.example.divinkas.cats.Model;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.divinkas.cats.CatsActivity;
import com.example.divinkas.cats.ErrActivity;
import com.example.divinkas.cats.MainActivity;
import com.example.divinkas.cats.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainModel {

    private Context context;
    private FirebaseUser user;

    public MainModel(Context context){
        this.context = context;
        FirebaseAuth authorization = FirebaseAuth.getInstance();
        user = authorization.getCurrentUser();
    }

    public boolean isNetworkState(){
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

    public boolean isAuthorizeUser(){
        return (user != null);
    }

    public Intent getViewIntentByName(String name){
        Intent intent = new Intent(context, MainActivity.class);

        if(name.equals("Error")){ intent = new Intent(context, ErrActivity.class); }
        if(name.equals("Cats")){ intent = new Intent(context, CatsActivity.class); }
        if(name.equals("Registration")){ intent = new Intent(context, RegistrationActivity.class); }

        return intent;
    }
}
