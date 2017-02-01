package com.ina.ina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class DiseaseActivity extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
    }


    public void selectItem(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId())
        {
            case R.id.diabetes:
                if(checked)
                {
                    selection.add("Diabetes");
                }
                else
                {
                    selection.remove("Diabetes");
                }
                break;
            case R.id.vegetarian:
                if(checked)
                {
                    selection.add("Vegetarian");
                }
                else
                {
                    selection.remove("Vegetarian");
                }
                break;
            case R.id.ovo_vegetarian:
                if(checked)
                {
                    selection.add("Ovo-Vegetarian");
                }
                else
                {
                    selection.remove("Ovo-Vegetarian");
                }
                break;
            case R.id.lacto_ovo_vegetarian:
                if(checked)
                {
                    selection.add("Lacto-Ovo-Vegetarian");
                }
                else
                {
                    selection.remove("Lacto-Ovo-Vegetarian");
                }
                break;
            case R.id.peszcetarian:
                if(checked)
                {
                    selection.add("Peszcetarian");
                }
                else
                {
                    selection.remove("Peszcetarian");
                }
                break;
            case R.id.vegan:
                if(checked)
                {
                    selection.add("Vegan");
                }
                else
                {
                    selection.remove("Vegan");
                }
                break;
            case R.id.kosher_only:
                if(checked)
                {
                    selection.add("Kosher Only");
                }
                else
                {
                    selection.remove("Kosher Only");
                }
                break;
            case R.id.no_pork:
                if(checked)
                {
                    selection.add("No Pork");
                }
                else
                {
                    selection.remove("No Pork");
                }
                break;
            case R.id.gluten_free:
                if(checked)
                {
                    selection.add("Glutten Free");
                }
                else
                {
                    selection.remove("Glutten Free");
                }
                break;
            case R.id.lactose_intolerant:
                if(checked)
                {
                    selection.add("Lactose Intolerant");
                }
                else
                {
                    selection.remove("Lactose Intolerant");
                }
                break;
            case R.id.wheat_allergy:
                if(checked)
                {
                    selection.add("Wheat Allergy");
                }
                else
                {
                    selection.remove("Wheat Allergy");
                }
                break;
            case R.id.peanut_allergy:
                if(checked)
                {
                    selection.add("Peanut Allergy");
                }
                else
                {
                    selection.remove("Peanut Allergy");
                }
                break;
            case R.id.egg_allergy:
                if(checked)
                {
                    selection.add("Egg Allergy");
                }
                else
                {
                    selection.remove("Egg Allergy");
                }
                break;
            case R.id.soy_allergy:
                if(checked)
                {
                    selection.add("Soy Allergy");
                }
                else
                {
                    selection.remove("Soy Allergy");
                }
                break;
            case R.id.fish_allergy:
                if(checked)
                {
                    selection.add("Fish Allergy");
                }
                else
                {
                    selection.remove("Fish Allergy");
                }
                break;
            case R.id.shellfish_allergy:
                if(checked)
                {
                    selection.add("Shellfish Allergy");
                }
                else
                {
                    selection.remove("Shellfish Allergy");
                }
                break;
            case R.id.shrimp_allergy:
                if(checked)
                {
                    selection.add("Shrimp Allergy");
                }
                else
                {
                    selection.remove("Shrimp Allergy");
                }
                break;








        }

    }

    public void saves(View v)
    {
        //Intent intent2 = new Intent(this, MainPageActivity.class);
        finish();

    }

    public void cancels(View v)
    {
        finish();

    }
}
