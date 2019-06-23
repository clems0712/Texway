package com.example.texway.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.texway.Fragment.FlashHistoFragment;
import com.example.texway.Fragment.ScoreFragment;
import com.example.texway.Fragment.SuggestFragment;
import com.example.texway.R;

public class MenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Texway");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        loadFragment(new FlashHistoFragment());

        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment fragment = null;

                        switch (item.getItemId()){

                            case R.id.action_flash:
                                fragment = new FlashHistoFragment();
                                break;
                            case R.id.action_suggest:
                                fragment = new SuggestFragment();
                                break;

                            case R.id.action_settings:
                                fragment = new ScoreFragment();
                                break;
                        }
                        return loadFragment(fragment);
                    }

    });

}

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}