package com.ina.ina;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity{
    EditText age,weight,height;

    String value_age, value_weight, value_height, gender;
    String pregnant, breastfeeding, nut, vegitarian, lactose, gluten;

    CheckBox Gm,Gf,Py,Pn,By,Bn,Ny,Nn,My,Mn,Ly,Ln,Gly,Gln;

    boolean BGm,BGf,BPy,BPn,BBy, BBn, BNy, BNn, BMy,BMn, BLy, BLn, BGly, BGln;

    String url = "http://diet.uoitscheduler.com/get_settings_api?user_id=32";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try{
            client.newCall(request).enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e){
                    e.printStackTrace();
                    Log.d("Failure", "The call has failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException{
                    if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

                    String jsonString = response.body().string();
                    try{
                        JSONObject infoJson = new JSONObject(jsonString);
                        value_age = infoJson.getString("age");
                        value_weight = infoJson.getString("weight");
                        value_height = infoJson.getString("height");
                        gender = infoJson.getString("gender");
                        pregnant = infoJson.getString("pregnant");
                        breastfeeding = infoJson.getString("breastfeeding");
                        nut = infoJson.getString("nut_free");
                        vegitarian = infoJson.getString("vegetarian");
                        lactose = infoJson.getString("lactose_free");
                        gluten = infoJson.getString("glutent_free");
                    }catch(JSONException e){
                        Log.d("Error", "JsonString Error");
                    }
                }
            });
        }catch(Exception e){

        }
        try {
            Thread.sleep(2500);
            Log.d("tester", "it worked");
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinDiet);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.activity_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        age = (EditText) findViewById(R.id.editText);
        weight = (EditText) findViewById(R.id.editText2);
        height = (EditText) findViewById(R.id.editText3);
        Gm = (CheckBox) findViewById(R.id.checkBox);
        Gf = (CheckBox) findViewById(R.id.checkBox2);
        Py = (CheckBox) findViewById(R.id.checkBox11);
        Pn = (CheckBox) findViewById(R.id.checkBox22);
        By = (CheckBox) findViewById(R.id.checkBox111);
        Bn = (CheckBox) findViewById(R.id.checkBox222);
        Ny = (CheckBox) findViewById(R.id.checkNutY);
        Nn = (CheckBox) findViewById(R.id.checkNutX);
        My = (CheckBox) findViewById(R.id.checkMeatY);
        Mn = (CheckBox) findViewById(R.id.checkMeatN);
        Ly = (CheckBox) findViewById(R.id.checkLactoseY);
        Ln = (CheckBox) findViewById(R.id.checkLactoseN);
        Gly = (CheckBox) findViewById(R.id.checkGlutenY);
        Gln = (CheckBox) findViewById(R.id.checkGlutenN);


        if (gender.equals("M")) {
            BGm = true;
            BGf = false;
        }
        else {
            BGf = true;
            BGm = false;
        }

        if(pregnant.equals("1")){
            BPy = true;
            BPn = false;
        }
        else {
            BPy = false;
            BPn = true;
        }

        if(breastfeeding.equals("1")) {
            BBy = true;
            BBn = false;
        }
        else{
            BBy = false;
            BBn = true;
        }

        if (nut.equals("1")){
            BNy = true;
            BNn = false;
        }
        else{
            BNy = false;
            BNn = true;
        }

        if(vegitarian.equals("1")) {
            BMy = true;
            BMn = false;
        }
        else{
            BMy = false;
            BMn = true;
        }

        if(lactose.equals("1")) {
            BLy = true;
            BLn = false;
        }
        else{
            BLy = false;
            BLn = true;
        }

        if(gluten.equals("1")){
            BGly = true;
            BGln = false;
        }
        else{
            BGly = false;
            BGln = true;
        }

        age.setText(value_age, TextView.BufferType.NORMAL);
        weight.setText(value_weight, TextView.BufferType.NORMAL);
        height.setText(value_height, TextView.BufferType.NORMAL);
        Gm.setChecked(BGm);
        Gf.setChecked(BGf);
        Py.setChecked(BPy);
        Pn.setChecked(BPn);
        By.setChecked(BBy);
        Bn.setChecked(BBn);
        Ny.setChecked(BNy);
        Nn.setChecked(BNn);
        My.setChecked(BMy);
        Mn.setChecked(BMn);
        Ly.setChecked(BLy);
        Ln.setChecked(BLn);
        Gly.setChecked(BGly);
        Gln.setChecked(BGln);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }
    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }
    public void diseases(View v)
    {
        Intent intent2 = new Intent(this, DiseaseActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void signUp(View v)
    {
        //Intent intent2 = new Intent(this, LoginActivity.class);
        //startActivityForResult(intent2, 2);
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}