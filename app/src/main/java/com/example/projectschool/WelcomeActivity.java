package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity implements LogInFragment.LogInListener, SignUpFragment.SignUpListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FragmentManager fragmentManager = getSupportFragmentManager();
        LogInFragment logInFragment = new LogInFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.myFragmentContainerView, logInFragment,
                "logInFragment");
        transaction.commit();
    }

    public void changeFragmentToSignUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SignUpFragment signUpFragment = new SignUpFragment();
// Start a new transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
// Add the fragment to the transaction
        transaction.replace(R.id.myFragmentContainerView, signUpFragment,
                "signUpFragment");
// Commit the transaction
        transaction.commit();
    }

    public void changeFragmentToLogIn(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        LogInFragment logInFragment = new LogInFragment();
// Start a new transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
// Add the fragment to the transaction
        transaction.replace(R.id.myFragmentContainerView, logInFragment,
                "logInFragment");
// Commit the transaction
        transaction.commit();

    }
}