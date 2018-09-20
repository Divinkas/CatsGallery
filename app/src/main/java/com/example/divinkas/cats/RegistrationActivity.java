package com.example.divinkas.cats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLoginReg, etPassReg, etPass2Reg;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        check();
    }

    private void check() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(this, CatsActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            init();
        }
    }

    private void init() {
        etLoginReg = findViewById(R.id.etRegLogin);
        etPassReg = findViewById(R.id.etRegPass);
        etPass2Reg = findViewById(R.id.etRegPass2);
        Button btnRegistrationAccount = findViewById(R.id.btnRegistrationAccount);
        btnRegistrationAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!etLoginReg.getText().toString().isEmpty()
                && !etPassReg.getText().toString().isEmpty()
                && etPassReg.getText().toString().equals(etPass2Reg.getText().toString())) {
            String email = etLoginReg.getText().toString();
            String password = etPassReg.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task2 -> {
                        if (task2.isSuccessful()) {
                            Intent intent = new Intent(this, CatsActivity.class);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "false auth!", Toast.LENGTH_SHORT).show();

                    });
                } else Toast.makeText(this, "Failed registration!", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
