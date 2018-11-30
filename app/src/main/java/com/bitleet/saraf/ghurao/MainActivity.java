package com.bitleet.saraf.ghurao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.AuthenticationCompleteListener {

    private FragmentManager manager;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Fragment fragment = null;

//        if (auth.getCurrentUser() == null){
//            fragment = new LoginFragment();
//        }else {
//            fragment = new EventFragment();
//        }

        if(user==null){
            fragment = new LoginFragment();
        }else {
            fragment = new EventFragment();
        }

        ft.add(R.id.fragmentContainer,fragment);
        ft.commit();
    }

    @Override
    public void onAuthComplete() {

        FragmentTransaction ft = manager.beginTransaction();
        EventFragment eventFragment = new EventFragment();
        ft.replace(R.id.fragmentContainer,eventFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
