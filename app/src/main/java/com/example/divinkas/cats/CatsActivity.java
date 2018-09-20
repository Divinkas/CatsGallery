package com.example.divinkas.cats;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Adapter.GalleryAdapter;
import com.example.divinkas.cats.Data.PicktureObj;
import com.example.divinkas.cats.Model.InitNavigation;
import com.example.divinkas.cats.Presenter.CatsPresenter;
import com.example.divinkas.cats.View.IcatsView;


public class CatsActivity extends MvpAppCompatActivity implements IcatsView {

    @InjectPresenter
    public CatsPresenter catsPresenter;

    private RecyclerView rvContainerImages;
    Toolbar mytoolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        catsPresenter = new CatsPresenter();
    }

    @Override
    public void showListImages(PicktureObj picktureObj) {
        rvContainerImages.setLayoutManager(new LinearLayoutManager(this));
        rvContainerImages.setAdapter(new GalleryAdapter(picktureObj.getHits(), this));
    }

    @Override
    public void findElementsView() {
        mytoolbar = findViewById(R.id.toolbar1);
        drawerLayout = findViewById(R.id.drawerLayoutMain);
        navigationView = findViewById(R.id.navigationViewMain);
        rvContainerImages = findViewById(R.id.rvContainerImages);

        InitNavigation initNavigation = new InitNavigation(this);
        initNavigation.init(mytoolbar, drawerLayout, navigationView);
    }
}
