package com.ina.ina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ina.ina.Data.Food;
import com.ina.ina.Database.FoodDatabase;

import java.util.ArrayList;

public class FoodInfo extends AppCompatActivity {

    TextView name;
    TextView specost;
    TextView displayAmount;
    private FoodDatabase spefood;
    double cost;
    String specific;
    String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        Intent get = getIntent();
        String ref = get.getStringExtra("chicken");
        int p = get.getIntExtra("position", 0);//instead of position we should just be bringing the id value over, so we can call the url and grab the information from there

        spefood = new FoodDatabase(this);

        final Food foodDisplay = spefood.getFood(ref);

        name = (TextView)findViewById(R.id.textView10);
        specost = (TextView)findViewById(R.id.textView9);
        displayAmount = (TextView)findViewById(R.id.textView12);

        name.setText(ref);

        //give the name, the cost, and the amount of nutrients per anything(varies for each item)
        //speobj = speediblefoodlist.get(p);
        cost = foodDisplay.getCost();
        Log.d("test cost", Double.toString(cost));
        specific = Double.toString(cost);
        specost.setText(specific);

        double changeAmount = Double.parseDouble(spefood.getFoodAmount(ref));
        double da = changeAmount * 100;

        amount = Double.toString(da) + " g";
        displayAmount.setText(amount);
    }
}
