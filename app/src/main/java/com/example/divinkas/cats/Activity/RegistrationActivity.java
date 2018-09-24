package com.example.divinkas.cats.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Activity.MainActivity;
import com.example.divinkas.cats.Presenter.RegistationPresenter;
import com.example.divinkas.cats.R;
import com.example.divinkas.cats.View.IregistationView;

public class RegistrationActivity extends MvpAppCompatActivity implements IregistationView {

    private EditText etLoginRegistration, etPasswordRegistration, etRepeatPasswordRegistration;

    private View.OnClickListener registrationClickListener;

    @InjectPresenter
    public RegistationPresenter registationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLoginRegistration = findViewById(R.id.etRegLogin);
        etPasswordRegistration = findViewById(R.id.etRegPass);
        etRepeatPasswordRegistration = findViewById(R.id.etRegPass2);
        Button btnRegistrationAccount = findViewById(R.id.btnRegistrationAccount);
        btnRegistrationAccount.setOnClickListener(registrationClickListener);

        registrationClickListener = v -> {
            if(!etLoginRegistration.getText().toString().isEmpty()
                    && !etPasswordRegistration.getText().toString().isEmpty()
                    && etPasswordRegistration.getText().toString().equals(etRepeatPasswordRegistration.getText().toString())) {

                String email = etLoginRegistration.getText().toString();
                String password = etPasswordRegistration.getText().toString();

                if(registationPresenter.RegistationNewUser(email, password, this)){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


}
