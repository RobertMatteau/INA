package com.ina.ina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ina.ina.Data.Food;
import com.ina.ina.Database.FoodDatabase;

import java.util.ArrayList;

public class FoodInfo extends AppCompatActivity {

    TextView name;
    TextView specost;
    private FoodDatabase spefood;
    Food speobj;
    double cost;
    String specific;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);


        Intent get = getIntent();
        String ref = get.getStringExtra("chicken");
        int p = get.getIntExtra("position", 0);

        spefood = new FoodDatabase(this);

        final ArrayList<Food> speediblefoodlist = spefood.getEdibleFood();


        name = (TextView)findViewById(R.id.textView10);
        specost = (TextView)findViewById(R.id.textView9);

        name.setText(ref);

        speobj = speediblefoodlist.get(p);
        cost = speobj.getCost();
        specific = Double.toString(cost);
        specost.setText(specific);





    }
}
