package com.ina.ina;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailedViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailedViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String TAG = "MainPageActivity";

    private float[] yData = {30, 40, 50, 60, 70};
    private String[] xData = {"Proteins", "Fats", "Vitamins", "Minerals", "Calories"};
    PieChart pieChart;

    private OnFragmentInteractionListener mListener;

    public DetailedViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailedViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailedViewFragment newInstance(String param1, String param2) {
        DetailedViewFragment fragment = new DetailedViewFragment();
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (FrameLayout) inflater.inflate(R.layout.fragment_detailed_view, container, false);

        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = (PieChart)getView().findViewById(R.id.idPieChart);

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

            }

            @Override
            public void onNothingSelected() {

            }
        });

        // Inflate the layout for this fragment
        return (FrameLayout)inflater.inflate(R.layout.fragment_detailed_view, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    //@Override
    //public void onAttach(Context context) {
    //    super.onAttach(context);
    //    if (context instanceof OnFragmentInteractionListener) {
     //       mListener = (OnFragmentInteractionListener) context;
    //    } else {
    //        throw new RuntimeException(context.toString()
    //                + " must implement OnFragmentInteractionListener");
    //    }
    //}

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
