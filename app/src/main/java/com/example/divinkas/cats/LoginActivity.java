package com.example.divinkas.cats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.divinkas.cats.Presenter.LoginPresenter;
import com.example.divinkas.cats.View.IloginView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class LoginActivity extends MvpAppCompatActivity implements IloginView, View.OnClickListener {

    private static final int RC_SIGN_IN = 228;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    private EditText etLogin, etPass;
    private Button btnLogin, btnReg;
    private SignInButton btnSingIn;

    @InjectPresenter
    public LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnSingIn);
        btnReg = findViewById(R.id.btnReg);
        btnSingIn = findViewById(R.id.btnSingInGoogle);

        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        btnSingIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException ignored) {
                Toast.makeText(this, "Авторизация провалена!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        setContentView(R.layout.activity_main); // it's progress bar
                        gotoCatsActivity();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSingIn:
                mailSingIn();
                break;
            case R.id.btnReg:
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnSingInGoogle:
                signIn();
                break;
        }
    }

    @Override
    public void mailSingIn() {
        if(!etLogin.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty()) {
            String email = etLogin.getText().toString();
            String password = etPass.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Executor) this, task -> {
                if(task.isSuccessful()) {
                    gotoCatsActivity();
                }
            });
        }
    }

    @Override
    public void gotoCatsActivity(){
        Intent intent = new Intent(this, CatsActivity.class);
        startActivity(intent);
        finish();
    }

}
