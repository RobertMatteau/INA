package com.ina.ina;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.TextView;
import android.view.View;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import android.content.SharedPreferences;
import android.app.Activity;


import com.ina.ina.Data.Solution;
import com.ina.ina.Database.FoodDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DailyMealPlanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DailyMealPlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyMealPlanFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> shoppingList1 = null;
    ArrayAdapter<String> adapter1 = null;
    ListView lv1 = null;

    private OnFragmentInteractionListener mListener;

    public DailyMealPlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyMealPlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyMealPlanFragment newInstance(String param1, String param2) {
        DailyMealPlanFragment fragment = new DailyMealPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String url4 = "http://diet.uoitscheduler.com/lp_api";
    private FoodDatabase food;
    Context context;
    String objective;
    String[] foods = new String[18];
    String[] values = new String[18];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        food = new FoodDatabase(getActivity());
        setHasOptionsMenu(true);
        OkHttpClient client = new OkHttpClient();

        Request solutionRequest = new Request.Builder().url(url4).build();
        try{
            client.newCall(solutionRequest).enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e){
                    e.printStackTrace();
                    Log.d("Failure", "The call has failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException{
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String jsonString = response.body().string();
                    Log.d("The result is...", jsonString);
                    try {
                        JSONObject solutionJson = new JSONObject(jsonString);
                        objective = (solutionJson.getString("objective"));
                        Log.d("Objective", objective);
                    }catch(JSONException e) {

                    }
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
                        JSONObject solutionJson = new JSONObject(jsonString);
                        JSONArray jsonValuesLP = solutionJson.getJSONArray("values");
                        values = new String[jsonValuesLP.length()];
                        for(int j=0;j< 18;j++){
                            values[j] = jsonValuesLP.getString(j);
                            Log.d("test3", values[j]);
                        }
                    }catch(JSONException e){
                        Log.d("Error 3", "Json string error at values");
                    }
                }
            });
        }catch(Exception e){

        }
        try {
            Thread.sleep(5500);
            Log.d("tester", "it worked");
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }

        shoppingList1 = new ArrayList<>();

        for(int i = 0; i < foods.length; i++){
            String name = food.getFoodName(foods[i]);

            double cost = Double.parseDouble(food.getFoodCost(foods[i]));
            Log.d("cost check", Double.toString(cost));
            double size = Double.parseDouble(values[i]);
            Log.d("size check", values[i]);

            double trueSize = size * 100;
            double sizeCompare = (Double.parseDouble(food.getFoodAmount(name))) * 100;
            trueSize = Math.round(trueSize * 1000);
            trueSize = trueSize/1000;
            String sizeDisplay= Double.toString(trueSize);

            double trueCost = (trueSize/sizeCompare) * cost;
            trueCost = Math.round(trueCost * 100);
            trueCost = trueCost/100;
            String costDisplay = Double.toString(trueCost);

            String item = name + ": $" + costDisplay + " for " + sizeDisplay +"g";
            shoppingList1.add(item);
        }

        Collections.sort(shoppingList1);

        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, shoppingList1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_daily_meal_plan, container, false);
        TextView costView = (TextView) rootView.findViewById(R.id.mealcost);
        costView.setText(objective);

        lv1 = (ListView) rootView.findViewById(R.id.listView);
        lv1.setAdapter(adapter1);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static String preferredCase(String original)
    {
        if (original.isEmpty())
            return original;

        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

    public static void storeArrayVal( ArrayList<String> inArrayList, Context context)
    {
        Set<String> WhatToWrite = new HashSet<String>(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putStringSet("myArray", WhatToWrite);
        prefEditor.commit();
    }

    public static ArrayList getArrayVal( Context dan)
    {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues",Activity.MODE_PRIVATE);
        Set<String> tempSet = new HashSet<String>();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<String>(tempSet);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
