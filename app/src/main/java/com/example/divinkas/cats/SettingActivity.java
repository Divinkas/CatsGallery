package com.example.divinkas.cats;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Model.InitNavigation;
import com.example.divinkas.cats.Presenter.CatsPresenter;
import com.example.divinkas.cats.Presenter.SettingPresenter;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.View.IsettingView;

public class SettingActivity extends MvpAppCompatActivity implements IsettingView, View.OnClickListener {

    EditText etSave;
    Button btnSave;
    Toolbar mytoolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @InjectPresenter
    SettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingPresenter = new SettingPresenter();
    }

    @Override
    public void onClick(View v) {
        if(!etSave.getText().toString().isEmpty()){
            settingPresenter.setDefaultTextQuery(etSave.getText().toString());
        }
    }

    @Override
    public void init() {
        etSave.setText(MainActivity.defaultAPI_params.q);
    }

    @Override
    public void findElements() {
        etSave = findViewById(R.id.etKey);
        btnSave = findViewById(R.id.btnSaveSetting);
        mytoolbar = findViewById(R.id.toolbarS);
        drawerLayout = findViewById(R.id.drawerLayoutS);
        navigationView = findViewById(R.id.navigationViewS);

        InitNavigation initNavigation = new InitNavigation(this);
        initNavigation.init(mytoolbar, drawerLayout, navigationView);
    }
}