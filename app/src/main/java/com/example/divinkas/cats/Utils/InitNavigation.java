package com.example.divinkas.cats.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.divinkas.cats.Activity.CatsActivity;
import com.example.divinkas.cats.Activity.MainActivity;
import com.example.divinkas.cats.Activity.ProfileActivity;
import com.example.divinkas.cats.R;
import com.example.divinkas.cats.Activity.SettingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class InitNavigation {
    private Context context;

    public InitNavigation(Context context) {
        this.context = context;
    }

    public void init(Toolbar toolbar, DrawerLayout drawerLayout, NavigationView navigationView) {
        toolbar.setTitle(R.string.app_name);

        navigationView.inflateHeaderView(R.layout.header_navigation);
        navigationView.inflateMenu(R.menu.navigation_list);

        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setOnMenuItemClickListener(item -> true);
        toolbar.setNavigationOnClickListener((View v) -> drawerLayout.openDrawer(navigationView));

        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = null;
            switch (item.getItemId()){
                case R.id.profile:
                    intent = new Intent(context, ProfileActivity.class);
                    break;
                case R.id.cats:
                    intent = new Intent(context, CatsActivity.class);
                    break;
                case R.id.setting:
                    intent = new Intent(context, SettingActivity.class);
                    break;
                case R.id.ex:
                    FirebaseAuth.getInstance().signOut();
                    intent = new Intent(context, MainActivity.class);
                    break;
            }
            if(intent!=null){ context.startActivity(intent); }
            return true;
        });


    }

}
