package com.example.coffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Intent intent350 = getIntent();
        int tabListener350 = intent350.getIntExtra("TabListener",-1);
        if(tabListener350 == -1){
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_news()).commit();
            }
        } else {
            if (tabListener350 == R.id.nav_order) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_order()).commit();

                bottomNav.setSelectedItemId(R.id.nav_order);
            }
        }
        bottomNav.setOnNavigationItemSelectedListener(navListener350);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener350 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_news:
                            selectedFragment = new fragment_news();
                            break;
                        case R.id.nav_order:
                            selectedFragment = new fragment_order();
                            break;
                        case R.id.nav_store:
                            selectedFragment = new fragment_store();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new fragment_profile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}