package com.example.divinkas.cats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Presenter.RegistationPresenter;
import com.example.divinkas.cats.View.IregistationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
