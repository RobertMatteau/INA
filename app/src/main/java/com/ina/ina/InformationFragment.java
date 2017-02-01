package com.ina.ina;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ina.ina.Data.Food;
import com.ina.ina.Database.FoodDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FoodDatabase food;
    private Food piecefood;
    ArrayAdapter<String> adapterfood;
    ArrayAdapter<String> foodnames;
    ArrayAdapter<String> costnames;
    AutoCompleteTextView autosearch;
    TextView i;
    String[] place;
    String chicken;
    String[] placeholder;
    int counter = 0;
    int counter2 = 1;
    ArrayList<String> ch;
    ArrayList<String> cts;
    int counter3 = 1;
    TextView foodcost;
    Food nutriend;


    double vita, vitd,vite,vitk,vitc,thia,ribo,nia,vitb6,fol,vitb12,panth,chol,calc,copp,fluor,iron,magne,manga,phos,sele,zinc,pota,sodi,carb,tpro,tfib;

    double cost;






    ArrayList<String> foodList = null;
    //ArrayList<String> cost = null;
    //ArrayList<String> amount = null;
    //ArrayList<String> stores = null;
    ArrayAdapter<String> adapter = null;
    Button add;
    //SimpleAdapter simple1 = null;
    // simple2 = null;
    //SimpleAdapter simple3 = null;
    ListView lv = null;

    private OnFragmentInteractionListener mListener;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);

        //foodList = getArrayVal(getActivity().getApplicationContext());
        //shoppingList = new ArrayList<>();
        //cost =new ArrayList<>();
        //amount = new ArrayList<>();
        //stores = new ArrayList<>();

        //Collections.addAll(shoppingList, "Whole Wheat Sliced Bread, $0.21, 2 slices, Walmart", "Orange Juice, $0.50, 1 Cup, Walmart", "Tofu, Silken, Raw, $0.25, 100g, No Frills", "Chicken Breast, Cooked, $0.70, 50g, Metro", "Milk, Skim, $0.30, 1 Cup, No Frills", "Banana, Raw, $0.60, 2 Whole, Walmart");
        //Collections.addAll(cost, "$0.21", "$0.50", "$0.25", "$0.70", "$0.30", "$0.60");
        //Collections.addAll(amount, "2 slices", "1 Cup", "100g", "50g", "1 Cup", "2 Whole");
        //Collections.addAll(stores, "Walmart", "Walmart", "NoFrills", "Metro", "No Frills", "Walmart");

        //shoppingList.addAll(Arrays.asList("Dog food", "Chapstick", "Bread"));
        //shoppingList.add("Sunscreen");
        //shoppingList.add("Toothpaste");

        //Collections.sort(foodList);
        //Collections.sort(cost);
        // Collections.sort(amount);
        //Collections.sort(stores);


        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodList);

        //simple1 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, cost);
        //simple2 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, amount);
        //simple3 = new SimpleAdapter(getActivity(), android.R.layout.simple_list_item_2, stores);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        lv = (ListView) rootView.findViewById(R.id.listView);


        ch = new ArrayList<String>();
        cts = new ArrayList<String>();






        //lv.setAdapter(adapter);

        food = new FoodDatabase(getActivity().getApplicationContext());
        i = (TextView)rootView.findViewById(R.id.ediblefood_textview );


        final ArrayList<Food> ediblefoodlist = food.getEdibleFood();

        final String[] ediblefoodsearch = new String[3994]; //3994
        for(int i=0; i < 3994; i++){
            Food a = ediblefoodlist.get(i);

            if (a == null){
                break;
            }

            ediblefoodsearch[i] = (a.name);


        }

        add = (Button)rootView.findViewById(R.id.buttontest);

        autosearch = (AutoCompleteTextView)rootView.findViewById(R.id.auto);
        adapterfood = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, ediblefoodsearch);
        autosearch.setAdapter(adapterfood);
        autosearch.setThreshold(1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = autosearch.getText().toString();
                //double b = piecefood.getCost();
                //placeholder = new String[counter2];

                /*place = new String[counter2];
                if(counter >= 1) {

                    for (int i = 0; i < counter2; i++) {
                        placeholder[i] = place[i];

                        if (placeholder[i] == null) {
                            break;
                        }
                    }
                }
                else
                {
                    place[counter] = a;
                }*/
                ch.add(a);






                //placeholder[counter]=place[counter];


                foodnames = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.ediblefoodrow,R.id.ediblefood_textview,ch);

                //place = null;

                //counter2++;

                Food foodinfo = food.getFoodByName(ediblefoodlist, ch.get(counter) );

                nutriend = ediblefoodlist.get(counter);


                vita += nutriend.getVitaminA();
                vitb6 += nutriend.getVitaminB();
                vitb12 += nutriend.getVitaminB12();
                vitc += nutriend.getVitaminC();
                vitd += nutriend.getVitaminD();
                vite += nutriend.getVitaminE();
                vitk += nutriend.getVitaminK();
                thia += nutriend.getThiamin();
                ribo += nutriend.getRiboflavin();
                nia += nutriend.getNiacin();
                fol += nutriend.getFolate();
                panth += nutriend.getPantothenicAcid();
                chol += nutriend.getCholine();
                calc += nutriend.getCalcium();
                copp += nutriend.getCopper();
                fluor += nutriend.getFluoride();
                iron += nutriend.getIron();
                magne += nutriend.getMagnesium();
                manga += nutriend.getMaganese();
                phos += nutriend.getPhosphorus();
                sele += nutriend.getSelenium();
                zinc += nutriend.getZinc();
                pota += nutriend.getPotassium();
                sodi += nutriend.getSodium();
                carb += nutriend.getCarbohydrate();
                tpro += nutriend.getProtein();
                tfib += nutriend.getFibre();





                lv.setAdapter(foodnames);





                counter++;







            }
        });





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                /*String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(foodList.get(position).trim())) {
                    removeElement(selectedItem, position);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                }*/
                Intent intent = new Intent(getActivity().getApplicationContext(), FoodInfo.class);

                chicken = ch.get(position);

                //long dexNum = id+1;
                intent.putExtra("chicken", chicken);
                intent.putExtra("position", position);

                //starts the pokemon viewing activity
                startActivity(intent);



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
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Item");
                final EditText input = new EditText(getActivity());
                //-----------

                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foodList.add(preferredCase(input.getText().toString()));
                        Collections.sort(foodList);
                        storeArrayVal(foodList, getActivity().getApplicationContext());
                        lv.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;*/

                Intent i = new Intent(getActivity().getApplicationContext(), NutrientsActivity.class);

                i.putExtra("vita",vita);
                i.putExtra("vitb6",vitb6);
                i.putExtra("vitb12",vitb12);
                i.putExtra("vitc",vitc);
                i.putExtra("vitd",vitd);
                i.putExtra("vite",vite);
                i.putExtra("vitk",vitk);
                i.putExtra("thia",thia);
                i.putExtra("ribo",ribo);
                i.putExtra("nia",nia);
                i.putExtra("fol",fol);
                i.putExtra("panth",panth);
                i.putExtra("chol",chol);
                i.putExtra("calc",calc);
                i.putExtra("copp",copp);
                i.putExtra("fluor",fluor);
                i.putExtra("iron",iron);
                i.putExtra("magne",magne);
                i.putExtra("manga",manga);
                i.putExtra("phos",phos);
                i.putExtra("sele",sele);
                i.putExtra("zinc",zinc);
                i.putExtra("pota",pota);
                i.putExtra("sodi",sodi);
                i.putExtra("carb",carb);
                i.putExtra("tpro",tpro);
                i.putExtra("tfib",tfib);

                startActivity(i);


            default:
                return super.onOptionsItemSelected(item);
        }




    }

    /*public static String preferredCase(String original)
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

    public void removeElement(String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                foodList.remove(position);
                Collections.sort(foodList);
                storeArrayVal(foodList, getActivity().getApplicationContext());
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
    }*/

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


