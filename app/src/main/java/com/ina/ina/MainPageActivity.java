package com.ina.ina;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerNonConfig;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "MainPageActivity";

    //upper amounts
    float value1 = 122; //protein
    float value2 = 96; //fat
    float value3 = 3000 + 1000 + 2000 + 2 + 2 + 35 + 10;
    float value4 = 2500 + 45 + 800 + 4000 + 9400 + 2300 + 40 + 10 + 11 + 400;
    float value5 = 2582; //energy

    String url = "http://diet.uoitscheduler.com/lp_api";
    String url2 = "http://diet.uoitscheduler.com/food_api?food_id=";

    float proteinAverage = 21;
    float fatAverage = 22;
    float vitaminAverage = 23;
    float mineralAverage = 24;
    float energyAverage = 25;

    String[] foods = new String[18];
    String[] values = new String[18];

    float protein;
    float fat;
    float energy;

    float mineral;
    float calcium;
    float iron;
    float magnesium;
    float phosphorus;
    float potassium;
    float sodium;
    float zinc;
    float copper;
    float manganese;
    float selenium;

    float vitamin;
    float thiamin;
    float vitaminA;
    float vitaminE;
    float vitaminC;
    float riboflavin;
    float niacin;
    float panthothenicAcid;

    float proteinGet;
    float fatGet;
    float energyGet;

    float calciumGet;
    float ironGet;
    float magnesiumGet;
    float phosphorusGet;
    float potassiumGet;
    float sodiumGet;
    float zincGet;
    float copperGet;
    float manganeseGet;
    float seleniumGet;

    float thiaminGet;
    float vitaminAGet;
    float vitaminEGet;
    float vitaminCGet;
    float riboflavinGet;
    float niacinGet;
    float panthothenicAcidGet;

    private float[] yData = {proteinAverage, fatAverage, vitaminAverage, mineralAverage, energyAverage};
    private String[] xData = {"Proteins", "Fats", "Vitamins", "Minerals", "Calories"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Log.d(TAG, "onCreate: starting to create chart");

        //get all values for protein, vitamins, minerals, etc.
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try {
            client.newCall(request).enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e){
                    e.printStackTrace();
                    Log.d("Failure", "The call has failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String jsonString = response.body().string();
                    try {
                        JSONObject solutionJson = new JSONObject(jsonString);
                        JSONArray jsonFoodLP = solutionJson.getJSONArray("foods");
                        foods = new String[jsonFoodLP.length()];
                        for (int i = 0; i < jsonFoodLP.length(); i++) {
                            foods[i] = jsonFoodLP.getString(i);
                            Log.d("test2", foods[i]);
                        }
                    }catch(JSONException e) {
                        Log.d("Error 2", "error at food lp");
                    }

                    try{
                        JSONObject solutionJson2 = new JSONObject(jsonString);
                        JSONArray jsonValuesLP = solutionJson2.getJSONArray("values");
                        values = new String[jsonValuesLP.length()];
                        for (int j = 0; j < jsonValuesLP.length(); j++){
                            values[j] = jsonValuesLP.getString(j);
                            Log.d("value check", values[j]);
                        }
                    }catch(JSONException e){
                        Log.d("Error 3 ", "Error at values lp");
                    }
                }
            });
        }catch(Exception e){

        }

        try{
            Thread.sleep(5000);
            Log.d("tester", "it worked");
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }

        for(int i = 0; i < foods.length; i++){
            String requestURL = url2 + foods[i];
            Request request2 = new Request.Builder().url(requestURL).build();
            final float value = Float.parseFloat(values[i]);

            try{
                client.newCall(request2).enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {e.printStackTrace();}

                    @Override
                    public void onResponse(Call call, Response response) throws IOException{
                        if(!response.isSuccessful()){
                            throw new IOException("Unexpected code" + response);
                        }
                        String jsonString = response.body().string();
                        try{
                            JSONObject foodJson = new JSONObject(jsonString);
                            proteinGet = (Float.parseFloat(foodJson.getString("protein")) * value);
                            protein = protein + proteinGet;

                            fatGet = (Float.parseFloat(foodJson.getString("fat")) * value);
                            fat = fat + fatGet;

                            energyGet = (Float.parseFloat(foodJson.getString("energy")) * value);
                            energy = energy + energyGet;

                            calciumGet = (Float.parseFloat(foodJson.getString("calcium")) * value);
                            calcium = calcium + calciumGet;
                            ironGet = (Float.parseFloat(foodJson.getString("iron")) * value);
                            iron = iron + ironGet;
                            magnesiumGet = (Float.parseFloat(foodJson.getString("magnesium")) * value);
                            magnesium = magnesium + magnesiumGet;
                            phosphorusGet = (Float.parseFloat(foodJson.getString("phosphorus")) * value);;
                            phosphorus = phosphorus + phosphorusGet;
                            potassiumGet = (Float.parseFloat(foodJson.getString("potassium")) * value);;
                            potassium = potassium + potassiumGet;
                            sodiumGet = (Float.parseFloat(foodJson.getString("sodium")) * value);;
                            sodium = sodium + sodiumGet;
                            zincGet = (Float.parseFloat(foodJson.getString("zinc")) * value);;
                            zinc = zinc + zincGet;
                            copperGet = (Float.parseFloat(foodJson.getString("copper")) * value);
                            copper = copper + copperGet;
                            manganeseGet = (Float.parseFloat(foodJson.getString("manganese")) * value);
                            manganese = manganese + manganeseGet;
                            seleniumGet = (Float.parseFloat(foodJson.getString("selenium")) * value);
                            selenium = selenium + seleniumGet;
                            mineral = mineral + calciumGet + ironGet + magnesiumGet + phosphorusGet + potassiumGet + sodiumGet + zincGet + copperGet + manganeseGet + seleniumGet;

                            thiaminGet = (Float.parseFloat(foodJson.getString("thiamin")) * value);
                            thiamin = thiamin + thiaminGet;
                            vitaminAGet = (Float.parseFloat(foodJson.getString("vitaminA")) * value);
                            vitaminA = vitaminA + vitaminAGet;
                            vitaminEGet = (Float.parseFloat(foodJson.getString("vitaminE")) * value);
                            vitaminE = vitaminE + vitaminEGet;
                            vitaminCGet = (Float.parseFloat(foodJson.getString("vitaminC")) * value);
                            vitaminC = vitaminC + vitaminCGet;
                            riboflavinGet = (Float.parseFloat(foodJson.getString("riboflavin")) * value);
                            riboflavin = riboflavin + riboflavinGet;
                            niacinGet = (Float.parseFloat(foodJson.getString("niacin")) * value);
                            niacin = niacin + niacinGet;
                            panthothenicAcidGet = (Float.parseFloat(foodJson.getString("pantothenicAcid")) * value);
                            panthothenicAcid = panthothenicAcid + panthothenicAcidGet;
                            vitamin = vitamin + thiaminGet + vitaminAGet + vitaminEGet + vitaminCGet + riboflavinGet + niacinGet + panthothenicAcidGet;
                        }catch(JSONException e){
                            Log.d("Error", "Json String Error");
                        }
                    }
                });
            }catch(Exception e){

            }
        }

        try{
            Thread.sleep(5000);
            Log.d("tester", "it worked");
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }

        float proteinAverage = ((protein)/(value1)) * 100;
        float fatAverage = ((fat)/value2) * 100;
        float energyAverage = ((energy)/value5) * 100;
        float mineralAverage = ((mineral)/value4) * 100;
        float vitaminAverage = ((vitamin)/value3) * 100;

        yData[0] = proteinAverage;
        yData[1] = fatAverage;
        yData[2] = vitaminAverage;
        yData[3] = mineralAverage;
        yData[4] = energyAverage;

        pieChart = (PieChart)findViewById(R.id.idPieChart1);


        pieChart.setDescription("");
        pieChart.setRotationEnabled(true);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Nutritonal Data");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        //more options just check out documentation

        addDataSet();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: value selected from chart");
                Log.d(TAG, "onValueSelected: "+ e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for(int i = 0; i<yData.length; i++){
                    if(yData[i] == Float.parseFloat(sales)){
                        pos1 = i;
                        break;
                    }
                }

                String employee = xData[pos1];

                if (employee.equals("Proteins")){

                    Bundle proteinBundle = new Bundle();
                    proteinBundle.putFloat("protein", protein);
                    ProteinsFragment fragment = new ProteinsFragment();
                    fragment.setArguments(proteinBundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(employee.equals("Fats")){

                    Bundle fatBundle = new Bundle();
                    fatBundle.putFloat("fats", fat);
                    FatsFragment fragment = new FatsFragment();
                    fragment.setArguments(fatBundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(employee.equals("Vitamins")){

                    Bundle vitaminBundle = new Bundle();
                    vitaminBundle.putFloat("thiamin", thiamin);
                    vitaminBundle.putFloat("vitaminA", vitaminA);
                    vitaminBundle.putFloat("vitaminE", vitaminE);
                    vitaminBundle.putFloat("vitaminC", vitaminC);
                    vitaminBundle.putFloat("riboflavin", riboflavin);
                    vitaminBundle.putFloat("niacin", niacin);
                    vitaminBundle.putFloat("pantothenicAcid", panthothenicAcid);
                    VitaminsFragment fragment = new VitaminsFragment();
                    fragment.setArguments(vitaminBundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(employee.equals("Minerals")){

                    Bundle mineralBundle = new Bundle();
                    mineralBundle.putFloat("calcium", calcium);
                    mineralBundle.putFloat("iron", iron);
                    mineralBundle.putFloat("magnesium", magnesium);
                    mineralBundle.putFloat("phosphorus", phosphorus);
                    mineralBundle.putFloat("potassium", potassium);
                    mineralBundle.putFloat("sodium", sodium);
                    mineralBundle.putFloat("zinc", zinc);
                    mineralBundle.putFloat("copper", copper);
                    mineralBundle.putFloat("manganese", manganese);
                    mineralBundle.putFloat("selenium", selenium);
                    MineralsFragment fragment = new MineralsFragment();
                    fragment.setArguments(mineralBundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                if(employee.equals("Calories")){

                    Bundle energyBundle = new Bundle();
                    energyBundle.putFloat("energy", energy);
                    CaloriesFragment fragment = new CaloriesFragment();
                    fragment.setArguments(energyBundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    
    private void addDataSet()
    {
        Log.d(TAG, "addDataSet: started adding data");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++)
        {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 0; i < xData.length; i++)
        {
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Nutrition");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c: ColorTemplate.JOYFUL_COLORS)
        {
            colors.add(c);
        }
        for (int c: ColorTemplate.VORDIPLOM_COLORS)
        {
            colors.add(c);
        }
        for (int c: ColorTemplate.LIBERTY_COLORS)
        {
            colors.add(c);
        }
        for (int c: ColorTemplate.COLORFUL_COLORS)
        {
            colors.add(c);
        }
        for (int c: ColorTemplate.PASTEL_COLORS)
        {
            colors.add(c);
        }


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setCustom(ColorTemplate.JOYFUL_COLORS, xData);


        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, 0);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        if(id == R.id.profile)
        {
            Intent i2 = new Intent(this, SignUpActivity.class);
            startActivityForResult(i2, 1);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if(id == R.id.log_out)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_scanner) {
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, new ScannerFragment()).commit();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new ScannerFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            // Handle the camera action
        } else */if (id == R.id.nav_detailed_view) {
            Intent i3 = new Intent(this, MainPageActivity.class);
            startActivityForResult(i3, 3);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, new DetailedViewFragment()).commit();
        } else if (id == R.id.nav_daily_meal_plan) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new DailyMealPlanFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_enter_informatio) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new InformationFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_meal_plan) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new MealPlanFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_protein) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new ProteinsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_fats) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new FatsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_vitamins) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new VitaminsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_minerals) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new MineralsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_calories) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new CaloriesFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
