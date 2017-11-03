package com.andresual.dev.urbankabinet.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Fragment.HomeFragment;
import com.andresual.dev.urbankabinet.Fragment.OthersFragment;
import com.andresual.dev.urbankabinet.Fragment.SearchFragment;
import com.andresual.dev.urbankabinet.Fragment.ShoppingCartFragment;
import com.andresual.dev.urbankabinet.R;

public class BottomNavigationActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        selectedFragment = HomeFragment.newInstance();
                        break;

                    case R.id.action_search:
                        selectedFragment = SearchFragment.newInstance();
                        break;

                    case R.id.action_shopping_cart:
                        selectedFragment = ShoppingCartFragment.newInstance();
                        break;

                    case R.id.action_others:
                        selectedFragment = OthersFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        //tampilkan fragment pertama secara manual
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }

    //set nama action bar pada setiap layout
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    Boolean twice = false;
    @Override
    public void onBackPressed() { //bisa dipake terus

        if (twice == true) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }

        twice = true;

        Toast.makeText(BottomNavigationActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 3000);
    }
}
