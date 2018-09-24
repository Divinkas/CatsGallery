package com.example.divinkas.cats.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.divinkas.cats.View.IregistationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@InjectViewState
public class RegistationPresenter extends MvpPresenter<IregistationView> {

    private FirebaseAuth firebaseAuth;

    public RegistationPresenter() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean RegistationNewUser(String mail, String password, Context context){
        AtomicBoolean result = new AtomicBoolean(false);
        firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener((Executor) this, task -> {
            if (task.isSuccessful()) {
                firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener((Executor) this, task2 -> {
                    if (task2.isSuccessful()) {
                        result.set(true);
                        Toast.makeText(context, "auth!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(context, "false auth!", Toast.LENGTH_SHORT).show();
                        result.set(false);
                });
            } else{
                Toast.makeText(context, "Failed registration!", Toast.LENGTH_SHORT).show();
                result.set(false);
            }

        });
        return result.get();

    }
}
