package com.ina.ina.Database;

/**
 * Created by 100522340 on 12/5/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ina.ina.Data.Food;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
public class FoodDatabase extends SQLiteAssetHelper{
    private static final String DATABASE_NAME ="prototype-nutrition-capstone.sqlite";
    private static final int DATABASE_VERSION = 1;

    public FoodDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Food> getEdibleFood(){
        SQLiteDatabase food = this.getReadableDatabase();
        String[] edibleArgument = {"edible"};
/*
        int id;
        String name;
        double vitaminA = 0;
        double vitaminD = 0;
        double vitaminE = 0;
        double vitaminK = 0;
        double vitaminC = 0;
        double thiamin = 0;
        double riboflavin = 0;
        double niacin = 0;
        double vitaminB = 0;
        double folate = 0;
        double vitaminB12 = 0;
        double pantothenicAcid = 0;
        double choline = 0;
        double calcium = 0;
        double copper = 0;
        double fluoride = 0;
        double iron = 0;
        double magnesium = 0;
        double maganese = 0;
        double phosphorus = 0;
        double selenium = 0;
        double zinc = 0;
        double potassium = 0;
        double sodium = 0;
        double carbohydrate = 0;
        double protein = 0;
        double fibre = 0;
*/
        Cursor find = food.query(
                "Foods",
                null,
                "edible == 1",
                null,
                null,
                null,
                null,
                null);

        ArrayList<Food> edibleList = new ArrayList<>();
        find.moveToFirst();

        while(!(find.isAfterLast())){
            int id = find.getInt(find.getColumnIndex("id"));
            String name = find.getString(find.getColumnIndex("name"));

            double cost = 12;
            if(find.getColumnIndex("cost") != 0){
                cost = find.getDouble((find.getColumnIndex("cost")));
            }

            double vitaminA = 0;
            if(find.getColumnIndex("n320") != 0) {
                vitaminA = find.getDouble((find.getColumnIndex("n320")));
            }

            double vitaminD = 0;
            if(find.getColumnIndex("n328") != 0) {
                vitaminD = find.getDouble((find.getColumnIndex("n328")));
            }

            double vitaminE = 0;
            if(find.getColumnIndex("n323") != 0) {
                vitaminE = find.getDouble((find.getColumnIndex("n323")));
            }

            double vitaminK = 0;
            if(find.getColumnIndex("n430") != 0) {
                vitaminK = find.getDouble((find.getColumnIndex("n430")));
            }

            double vitaminC = 0;
            if(find.getColumnIndex("n401") != 0) {
                vitaminC = find.getDouble((find.getColumnIndex("n401")));
            }

            double thiamin = 0;
            if(find.getColumnIndex("n404") != 0) {
                thiamin = find.getDouble((find.getColumnIndex("n404")));
            }

            double riboflavin = 0;
            if(find.getColumnIndex("n405") != 0) {
                riboflavin = find.getDouble((find.getColumnIndex("n405")));
            }

            double niacin = 0;
            if(find.getColumnIndex("n406") != 0) {
                niacin = find.getDouble((find.getColumnIndex("n406")));
            }

            double vitaminB = 0;
            if(find.getColumnIndex("n415") != 0) {
                vitaminB = find.getDouble((find.getColumnIndex("n415")));
            }
            double folate = 0;
            if(find.getColumnIndex("n417") != 0) {
                folate = find.getDouble((find.getColumnIndex("n417")));
            }

            double vitaminB12 = 0;
            if(find.getColumnIndex("n418") != 0) {
                vitaminB12 = find.getDouble((find.getColumnIndex("n418")));
            }

            double pantothenicAcid = 0;
            if(find.getColumnIndex("n410") != 0) {
                pantothenicAcid = find.getDouble((find.getColumnIndex("n410")));
            }

            double choline = 0;
            if(find.getColumnIndex("n421") != 0) {
                choline = find.getDouble((find.getColumnIndex("n421")));
            }

            double calcium = 0;
            if(find.getColumnIndex("n301") != 0) {
                calcium = find.getDouble((find.getColumnIndex("n301")));
            }

            double copper = 0;
            if(find.getColumnIndex("n312") != 0) {
                copper = find.getDouble((find.getColumnIndex("n312")));
            }

            double flouride = 0;
            if(find.getColumnIndex("n313") != 0) {
                flouride = find.getDouble((find.getColumnIndex("n313")));
            }

            double iron = 0;
            if(find.getColumnIndex("n303") != 0) {
                iron = find.getDouble((find.getColumnIndex("n303")));
            }

            double magnesium = 0;
            if(find.getColumnIndex("n304") != 0) {
                magnesium = find.getDouble((find.getColumnIndex("n304")));
            }

            double maganese = 0;
            if(find.getColumnIndex("n315") != 0) {
                maganese = find.getDouble((find.getColumnIndex("n315")));
            }

            double phosphorus = 0;
            if(find.getColumnIndex("n305") != 0) {
                phosphorus = find.getDouble((find.getColumnIndex("n305")));
            }

            double selenium = 0;
            if(find.getColumnIndex("n317") != 0) {
                selenium = find.getDouble((find.getColumnIndex("n317")));
            }

            double zinc = 0;
            if(find.getColumnIndex("n309") != 0) {
                zinc = find.getDouble((find.getColumnIndex("n309")));
            }

            double potassium = 0;
            if(find.getColumnIndex("n309") != 0) {
                potassium = find.getDouble((find.getColumnIndex("n309")));
            }

            double sodium = 0;
            if(find.getColumnIndex("n307") != 0) {
                sodium = find.getDouble((find.getColumnIndex("n401")));
            }

            double carbohydrate = 0;
            if(find.getColumnIndex("n205") != 0) {
                carbohydrate = find.getDouble((find.getColumnIndex("n205")));
            }

            double protein = 0;
            if(find.getColumnIndex("n203") != 0) {
                protein = find.getDouble((find.getColumnIndex("n203")));
            }

            double fibre = 0;
            if(find.getColumnIndex("n291") != 0) {
                fibre = find.getDouble((find.getColumnIndex("n291")));
            }

            Food numnum = new Food(id, name, cost, vitaminA, vitaminD, vitaminE, vitaminK, vitaminC,
                    thiamin, riboflavin, niacin, vitaminB, folate, vitaminB12, pantothenicAcid,
                    choline, calcium, copper, flouride, iron, magnesium, maganese, phosphorus,
                    selenium, zinc, potassium, sodium, carbohydrate, protein, fibre);

            edibleList.add(numnum);
            find.moveToNext();
        }
        find.close();
        food.close();
        return edibleList;
    }

    public Food getFoodByName(ArrayList<Food> list, String name){
        Food fuud = new Food(0, "", 30,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

        for (int i=0; i < list.size(); i++){
            Food compare = list.get(i);
            String comp = compare.getName();

            if(comp == name){
                fuud = compare;
                return fuud;
            }
        }
        return fuud;
    }
}

