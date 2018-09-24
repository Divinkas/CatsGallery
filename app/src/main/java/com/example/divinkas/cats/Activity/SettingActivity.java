package com.example.divinkas.cats.Activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.R;
import com.example.divinkas.cats.Retrofit.DefaultAPI_params;
import com.example.divinkas.cats.Utils.InitNavigation;
import com.example.divinkas.cats.Presenter.SettingPresenter;
import com.example.divinkas.cats.View.IsettingView;

public class SettingActivity extends MvpAppCompatActivity implements IsettingView {

    private EditText etSave;
    private Button btnSave;
    private Toolbar mytoolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View.OnClickListener dataChangeListener;

    @InjectPresenter
    public SettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        etSave = findViewById(R.id.etKey);
        btnSave = findViewById(R.id.btnSaveSetting);
        mytoolbar = findViewById(R.id.toolbarS);
        drawerLayout = findViewById(R.id.drawerLayoutS);
        navigationView = findViewById(R.id.navigationViewS);

        InitNavigation initNavigation = new InitNavigation(this);
        initNavigation.init(mytoolbar, drawerLayout, navigationView);

        etSave.setText(DefaultAPI_params.q);
        btnSave.setOnClickListener(dataChangeListener);

        dataChangeListener = v -> {
            if(!etSave.getText().toString().isEmpty()){
                settingPresenter.setDefaultTextQuery(etSave.getText().toString());
            }
        };

    }

}
