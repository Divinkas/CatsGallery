package com.example.divinkas.cats.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.R;
import com.example.divinkas.cats.Utils.InitNavigation;
import com.example.divinkas.cats.Presenter.ProfilePresenter;
import com.example.divinkas.cats.View.IprofileView;

public class ProfileActivity extends MvpAppCompatActivity implements IprofileView {

    @InjectPresenter
    public ProfilePresenter profilePresenter;

    private TextView tvName, tvMail;
    private ImageView imageView;
    private Toolbar mytoolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvProfileName);
        tvMail = findViewById(R.id.tvProfileMail);
        imageView = findViewById(R.id.imvProfile);
        mytoolbar = findViewById(R.id.toolbarPr);
        drawerLayout = findViewById(R.id.drawerLayoutPr);
        navigationView = findViewById(R.id.navigationViewPr);

        InitNavigation initNavigation = new InitNavigation(this);
        initNavigation.init(mytoolbar, drawerLayout, navigationView);

        tvName.setText(profilePresenter.getNameUser());
        tvMail.setText(profilePresenter.getMailUser());
        profilePresenter.setImage(imageView, this);
    }


}
