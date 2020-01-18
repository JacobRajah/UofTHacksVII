package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class RecommendedPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_page);

        dl = (DrawerLayout)findViewById(R.id.activity_recommended_page);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.Home:
                        Intent intent = new Intent(RecommendedPage.this, RecipeSearch.class);
                        startActivity(intent);
                    case R.id.SignIn:
                        Intent intent1 = new Intent(RecommendedPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.QRcode:
                        Intent intent2 = new Intent(RecommendedPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Search:
                        Intent intent3 = new Intent(RecommendedPage.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}