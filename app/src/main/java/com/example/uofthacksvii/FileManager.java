package com.example.uofthacksvii;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    private ArrayList<Recipe> recipes;

    public FileManager(Context context) {
        recipes = parseData(context);
    }

    public ArrayList<Recipe> parseData(Context context) {
        ArrayList<Recipe> resultList = new ArrayList<Recipe>();
        File file = new File("recipe_data.txt");

        try {
            FileInputStream dataStream = context.openFileInput("recipe_data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split("\t");
                if(row.length == 14) {
                    Recipe recipe = new Recipe(row);
                    resultList.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<Recipe> searchByTimeAvailable(int time) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getTime() <= time) {
                results.add(recipes.get(i));
            }
        }

        return results;
    }

    public ArrayList<Recipe> searchByCuisine(String cuisine) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getCuisine_type().equals(cuisine)) {
                results.add(recipes.get(i));
            }
        }
        return results;
    }

    public ArrayList<Recipe> searchByMealType(int type) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++) {
            for(int j = 0; j < 3; j++) {
                if(j == type && recipes.get(i).getMeal_type()[j] == 1) {
                    results.add(recipes.get(i));
                }
            }
        }

        return results;
    }

    /*public ArrayList<Recipe> searchByNutrition() {

    }*/
}