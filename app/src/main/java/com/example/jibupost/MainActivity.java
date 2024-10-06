package com.example.jibupost;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jibupost.fragments.Chat;
import com.example.jibupost.fragments.Add;
import com.example.jibupost.fragments.CalendarFragment;
import com.example.jibupost.fragments.Home;
import com.example.jibupost.fragments.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.nav_planner) {
                        selectedFragment = new Home();
                    } else if (item.getItemId() == R.id.nav_activity) {
                        selectedFragment = new Chat();
                    } else if (item.getItemId() == R.id.nav_add) {
                        selectedFragment = new Add();
                    } else if (item.getItemId() == R.id.nav_calendar) {
                        selectedFragment = new CalendarFragment();
                    } else if (item.getItemId() == R.id.nav_profile) {
                        selectedFragment = new Profile();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

}