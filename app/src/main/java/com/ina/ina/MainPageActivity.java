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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "MainPageActivity";

    private float[] yData = {30, 40, 50, 60, 70};
    private String[] xData = {"Proteins", "Fats", "Vitamins", "Minerals", "Calories"};
    PieChart pieChart;

    ArrayList<String> shoppingList = null;
    ListView lv = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart)findViewById(R.id.idPieChart1);

        pieChart.setDescription("");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Nutritonal Data");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
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
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, new ProteinsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if(employee.equals("Fats")){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, new FatsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if(employee.equals("Vitamins")){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, new VitaminsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if(employee.equals("Minerals")){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, new MineralsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if(employee.equals("Calories")){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
                    transaction.replace(R.id.content_frame, new CaloriesFragment());
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

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

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
        //colors.add(Color.BLUE);
        //colors.add(Color.RED);
        //colors.add(Color.GREEN);
        //colors.add(Color.CYAN);
        //colors.add(Color.MAGENTA);
        //colors.add(Color.GRAY);
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
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
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
        //if (id == R.id.action_sort)
        //{
        //    Collections.sort(shoppingList);
        //    lv.setAdapter(adapter);
        //    return false;
        //}


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scanner) {
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, new ScannerFragment()).commit();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new ScannerFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_detailed_view) {
            Intent i3 = new Intent(this, MainPageActivity.class);
            startActivityForResult(i3, 3);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //getFragmentManager().beginTransaction().replace(R.id.content_frame, new DetailedViewFragment()).commit();

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
        } else if (id == R.id.nav_share) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new ScannerFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_send) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit);
            transaction.replace(R.id.content_frame, new ScannerFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
