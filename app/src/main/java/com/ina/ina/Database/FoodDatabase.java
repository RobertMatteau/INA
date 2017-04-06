package com.ina.ina.Database;

/**
 * Created by 100522340 on 12/5/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ina.ina.Data.Food;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FoodDatabase extends SQLiteAssetHelper{
    private static final String DATABASE_NAME ="newfood.sqlite";
    private static final int DATABASE_VERSION = 1;

    ArrayList<Food> edibleList;
    ArrayList<Food> foodList;

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
                null,
                null,
                null,
                null,
                null,
                null);

        edibleList = new ArrayList<>();
        find.moveToFirst();

        while(!(find.isAfterLast())){
            int id = find.getInt(find.getColumnIndex("field1"));

            String url = "http://diet.uoitscheduler.com/food_api?food_id=" + Integer.toString(id);
            Request request = new Request.Builder().url(url).build();
            getFoodInfo(request);
            try {
                Thread.sleep(20);
            }catch(InterruptedException e) {
                Log.d("Stopped", "the first interrupt");
            }
            find.moveToNext();
        }
        try {
            Thread.sleep(20000);
            find.close();
            food.close();
            return edibleList;
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }
        return edibleList;
    }

    public void getFoodInfo (final Request request){
        OkHttpClient client = new OkHttpClient();

        try{
            client.newCall(request).enqueue(new Callback(){

                @Override
                public void onFailure(Call call, IOException e) {e.printStackTrace();}

                @Override
                public void onResponse(Call call, Response response) throws IOException{
                    if (!response.isSuccessful()){
                        throw new IOException("Unexpected code " + response);
                    }
                    String jsonString = response.body().string();
                    try{
                        JSONObject foodJson = new JSONObject(jsonString);
                        final Food food = new Food(
                                foodJson.getInt("id"),
                                foodJson.getString("short_name"),
                                Double.parseDouble(foodJson.getString("cost")),
                                Double.parseDouble(foodJson.getString("vitaminA")),
                                Double.parseDouble(foodJson.getString("vitaminD")),
                                Double.parseDouble(foodJson.getString("vitaminE")),
                                Double.parseDouble(foodJson.getString("vitaminK")),
                                Double.parseDouble(foodJson.getString("vitaminC")),
                                Double.parseDouble(foodJson.getString("thiamin")),
                                Double.parseDouble(foodJson.getString("riboflavin")),
                                Double.parseDouble(foodJson.getString("niacin")),
                                0.0,
                                0.0,
                                0.0,
                                Double.parseDouble(foodJson.getString("pantothenicAcid")),
                                Double.parseDouble(foodJson.getString("choline")),
                                Double.parseDouble(foodJson.getString("calcium")),
                                Double.parseDouble(foodJson.getString("copper")),
                                Double.parseDouble(foodJson.getString("fluoride")),
                                Double.parseDouble(foodJson.getString("iron")),
                                Double.parseDouble(foodJson.getString("magnesium")),
                                Double.parseDouble(foodJson.getString("manganese")),
                                Double.parseDouble(foodJson.getString("phosphorus")),
                                Double.parseDouble(foodJson.getString("selenium")),
                                Double.parseDouble(foodJson.getString("zinc")),
                                Double.parseDouble(foodJson.getString("potassium")),
                                Double.parseDouble(foodJson.getString("sodium")),
                                Double.parseDouble(foodJson.getString("carbohydrate")),
                                Double.parseDouble(foodJson.getString("protein")),
                                Double.parseDouble(foodJson.getString("fiber")),
                                Double.parseDouble(foodJson.getString("energy")),
                                Double.parseDouble(foodJson.getString("fat"))
                        );
                        edibleList.add(food);
                        Log.d("test", food.getName());
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(Exception e){

        }
    }

    public ArrayList<Food> getBasicFoodInfo(){
        SQLiteDatabase food = this.getReadableDatabase();

        Cursor find = food.query(
                "Foods",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        foodList = new ArrayList<>();
        find.moveToFirst();

        while(!(find.isAfterLast())) {
            int id = find.getInt(find.getColumnIndex("field1"));
            String name = find.getString(find.getColumnIndex("field3"));
            String cost = find.getString(find.getColumnIndex("field8"));
            double trueCost = Double.parseDouble(cost);

            final Food basicFood = new Food(id, name, trueCost, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            foodList.add(basicFood);
            find.moveToNext();
        }

        find.close();
        food.close();

        return foodList;
    }

    public Food getFood(String name){
        SQLiteDatabase food = this.getReadableDatabase();
        String[] select = new String[1];
        select[0] = name;

        Cursor find = food.query(
                "Foods",
                null,
                "field3 == ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();
        Food basicFood = new Food(0, "blank", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        while(!(find.isAfterLast())) {
            int id = find.getInt(find.getColumnIndex("field1"));
            String cost = find.getString(find.getColumnIndex("field8"));
            double trueCost = Double.parseDouble(cost);

            basicFood = new Food(id, name, trueCost, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            find.moveToNext();
        }

        find.close();
        food.close();

        return basicFood;
    }

    public String getFoodAmount(String name){
        SQLiteDatabase food = this.getReadableDatabase();
        String[] select = new String[1];
        select[0] = name;

        Cursor find = food.query(
                "Foods",
                null,
                "field3 == ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();
        String amount = "";

        while(!(find.isAfterLast())) {
            double am = find.getDouble(find.getColumnIndex("field7"))*100;
            amount = Double.toString(am);

            find.moveToNext();
        }

        find.close();
        food.close();

        return amount;
    }

    public String getMealPlanItem(String id){
        SQLiteDatabase food = this.getReadableDatabase();
        String item = "";
        String[] select = new String[1];
        select[0] = id;

        Cursor find = food.query(
                "Foods",
                null,
                "field1 == ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();

        while(!(find.isAfterLast())) {
            String itemName = find.getString(find.getColumnIndex("field3"));
            Log.d("test1", itemName);
            String itemPrice = find.getString(find.getColumnIndex("field8"));
            Log.d("test2", itemPrice);
            Double itemSize = (find.getDouble(find.getColumnIndex("field7"))) * 100;
            Log.d("test3", itemSize.toString());

            item = itemName + ", $" + itemPrice + " per: " + itemSize.toString() + " grams";
            find.moveToNext();
        }

        find.close();
        food.close();

        return item;
    }

    public String getFoodId(String name){
        SQLiteDatabase food = this.getReadableDatabase();
        String foodID = "0";
        String[] select = new String[1];
        select[0] = name;

        Cursor find = food.query(
                "Foods",
                null,
                "field3 == ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();

        while(!(find.isAfterLast())) {
            int id = find.getInt(find.getColumnIndex("field1"));
            foodID = Integer.toString(id);
            find.moveToNext();
        }

        find.close();
        food.close();

        return foodID;
    }

    public String getFoodCost(String id){
        SQLiteDatabase food = this.getReadableDatabase();
        String cost = "0";
        String[] select = new String[1];
        select[0] = id;

        Cursor find = food.query(
                "Foods",
                null,
                "field1 = ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();

        while(!(find.isAfterLast())) {
            cost = find.getString(find.getColumnIndex("field8"));

            find.moveToNext();
        }

        find.close();
        food.close();

        return cost;
    }

    public String getFoodName(String id){
        SQLiteDatabase food = this.getReadableDatabase();
        String name = "0";
        String[] select = new String[1];
        select[0] = id;

        Cursor find = food.query(
                "Foods",
                null,
                "field1 = ?",
                select,
                null,
                null,
                null,
                null);

        find.moveToFirst();

        while(!(find.isAfterLast())) {
            name = find.getString(find.getColumnIndex("field3"));

            find.moveToNext();
        }

        find.close();
        food.close();

        return name;
    }

    public Food getFoodByName(ArrayList<Food> list, String name){
        Food fuud = new Food(0, "", 30,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

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

