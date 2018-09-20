package com.example.divinkas.cats.Presenter;

import android.content.Context;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.divinkas.cats.R;
import com.example.divinkas.cats.View.IproileView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<IproileView> {
    private FirebaseUser user;

    public ProfilePresenter(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        getViewState().findElements();
        getViewState().inputData();
    }

    public String getNameUser() {
        return user.getDisplayName();
    }

    public String getMailUser(){
        return user.getEmail();
    }
    public void setImage(ImageView imageView, Context context){
        Glide.with(context)
                .load(user.getPhotoUrl())
                .error(R.drawable.default_user_ico)
                .centerCrop()
                .into(imageView);
    }
}
