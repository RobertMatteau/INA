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
import android.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;


import com.ina.ina.Database.FoodDatabase;

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
 * {@link MealPlanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealPlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealPlanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ArrayList<String> shoppingList = null;
    //ArrayList<String> cost = null;
    //ArrayList<String> amount = null;
    //ArrayList<String> stores = null;
    ArrayAdapter<String> adapter = null;
    //SimpleAdapter simple1 = null;
    // simple2 = null;
    //SimpleAdapter simple3 = null;
    ListView lv = null;

    private OnFragmentInteractionListener mListener;

    public MealPlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealPlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealPlanFragment newInstance(String param1, String param2) {
        MealPlanFragment fragment = new MealPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String url = "http://diet.uoitscheduler.com/base_foods_api?user_id=32";
    String url2 = "http://diet.uoitscheduler.com/remove_food_api?";
    String url3 = "http://diet.uoitscheduler.com/add_food_api?";
    String baseFoods;
    private FoodDatabase food;
    Context context;

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

        Request baseFoodRequest = new Request.Builder().url(url).build();
        try{
            client.newCall(baseFoodRequest).enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e){e.printStackTrace();}

                @Override
                public void onResponse(Call call, Response response) throws IOException{
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    baseFoods = response.body().string();
                    Log.d("test", baseFoods);

                }
            });
        }catch(Exception e){

        }
        try {
            Thread.sleep(1500);
            Log.d("tester", "it worked");
        }catch(InterruptedException e){
            Log.d("Stoped", "There was an interrupt");
        }

        String[] food_ids = baseFoods.split("[,]");

        String[] split1 = food_ids[0].split("[\"]");

        food_ids[0] = split1[3];
        Log.d("final test 1", food_ids[0]);
        Log.d("final test 1", food_ids[1]);

        //shoppingList = getArrayVal(getActivity().getApplicationContext());
        shoppingList = new ArrayList<>();
        //cost =new ArrayList<>();
        //amount = new ArrayList<>();
        //stores = new ArrayList<>();

        for(int i = 0; i < food_ids.length; i++){
            String item = food.getMealPlanItem(food_ids[i]);
            shoppingList.add(item);
        }

        //Collections.addAll(shoppingList, "Whole Wheat Sliced Bread, $0.21, 2 slices, Walmart", "Orange Juice, $0.50, 1 Cup, Walmart", "Tofu, Silken, Raw, $0.25, 100g, No Frills", "Chicken Breast, Cooked, $0.70, 50g, Metro", "Milk, Skim, $0.30, 1 Cup, No Frills", "Banana, Raw, $0.60, 2 Whole, Walmart");
        //Collections.addAll(cost, "$0.21", "$0.50", "$0.25", "$0.70", "$0.30", "$0.60");
        //Collections.addAll(amount, "2 slices", "1 Cup", "100g", "50g", "1 Cup", "2 Whole");
        //Collections.addAll(stores, "Walmart", "Walmart", "NoFrills", "Metro", "No Frills", "Walmart");

        //shoppingList.addAll(Arrays.asList("Dog food", "Chapstick", "Bread"));
        //shoppingList.add("Sunscreen");
        //shoppingList.add("Toothpaste");

        Collections.sort(shoppingList);
        //Collections.sort(cost);
       // Collections.sort(amount);
        //Collections.sort(stores);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, shoppingList);
        //simple1 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, cost);
        //simple2 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, amount);
        //simple3 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, stores);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meal_plan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(shoppingList.get(position).trim())) {
                    removeElement(selectedItem, position); //alter here
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch(item.getItemId()){

            case R.id.action_add:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Item");
                final EditText input = new EditText(getActivity());
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameInput = input.getText().toString();
                        Log.d("name test", nameInput);

                        String foodID = food.getFoodId(nameInput);
                        Log.d("check id", foodID);
                        int checkID = Integer.parseInt(foodID);

                        if(checkID == 0){
                            Toast.makeText(getActivity(), "This food item is not registered in our database", Toast.LENGTH_LONG).show();
                        }
                        else {
                            OkHttpClient client = new OkHttpClient();
                            String addURL = url3 + "food_id=" + checkID + "&user_id=32";

                            Request baseFoodRequest = new Request.Builder().url(addURL).build();
                            try{
                                client.newCall(baseFoodRequest).enqueue(new Callback(){
                                    @Override
                                    public void onFailure(Call call, IOException e){e.printStackTrace();}

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException{
                                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                                        Log.d("test", "It Worked!!!");
                                    }
                                });
                            }catch(Exception e){

                            }
                            try {
                                Thread.sleep(2500);
                                Log.d("tester", "it worked again");
                            }catch(InterruptedException e){
                                Log.d("Stoped", "There was an interrupt");
                            }

                            String addCost = food.getFoodCost(foodID);
                            String addAmount = food.getFoodAmount(input.getText().toString());



                            String addItem = input.getText().toString() + ", $" + addCost + " per: "+ addAmount + " grams";

                            shoppingList.add(addItem);
                            Collections.sort(shoppingList);
                            storeArrayVal(shoppingList, getActivity().getApplicationContext());
                            lv.setAdapter(adapter);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }



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

    public void removeElement(final String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] food_info = selectedItem.split("[,]");
                String foodName = food_info[0];
                Log.d("name test", foodName);
                String removeID = food.getFoodId(foodName);

                OkHttpClient client = new OkHttpClient();
                String removeURL = url2 + "food_id=" + removeID + "&user_id=32";

                Request baseFoodRequest = new Request.Builder().url(removeURL).build();
                try{
                    client.newCall(baseFoodRequest).enqueue(new Callback(){
                        @Override
                        public void onFailure(Call call, IOException e){e.printStackTrace();}

                        @Override
                        public void onResponse(Call call, Response response) throws IOException{
                            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                            Log.d("test", "It Worked!!!");
                        }
                    });
                }catch(Exception e){

                }
                try {
                    Thread.sleep(1500);
                    Log.d("tester", "it worked again");
                }catch(InterruptedException e){
                    Log.d("Stoped", "There was an interrupt");
                }

                shoppingList.remove(position);
                Collections.sort(shoppingList);
                storeArrayVal(shoppingList, getActivity().getApplicationContext());
                lv.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
