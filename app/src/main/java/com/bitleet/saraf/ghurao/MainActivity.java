package com.bitleet.saraf.ghurao;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.AuthenticationCompleteListener {

    private DrawerLayout drawerLayout;
    private FragmentManager manager;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Fragment fragment = null;

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this,
                                "navigationView item clicked",
                                Toast.LENGTH_SHORT).show();
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
//        if (auth.getCurrentUser() == null){
//            fragment = new LoginFragment();
//        }else {
//            fragment = new EventFragment();
//

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
