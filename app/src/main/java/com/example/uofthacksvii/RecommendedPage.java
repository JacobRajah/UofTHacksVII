package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecommendedPage extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_page);

        // Show entries
        ArrayList<String[]> resultList = new ArrayList<String[]>();
        InputStream dataStream = getResources().openRawResource(R.raw.recipe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split("\t");
                resultList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String[] data = {"Spaghetti", "Pizza", "Your Mom"}; //Fake test strings
        addEntry(resultList);
        // Make Navigation View
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
                        Intent intent = new Intent(RecommendedPage.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(RecommendedPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(RecommendedPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
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

    public void addEntry(ArrayList<String[]> entries) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scrollWindow);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(RecommendedPage.this, RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });

        for(int i = 0; i < entries.size(); i++) {
            if(entries.get(i).length > 0) {
                View entry = getLayoutInflater().inflate(R.layout.entry, null);
                TextView textView = (TextView) entry.findViewById(R.id.textView4);
                textView.setText(entries.get(i)[0]);
                linearLayout.addView(entry);
            }

        }
    }
}
