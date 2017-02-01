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
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    public FatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FatsFragment.
     */

    private TextView tvX, tvY;
    BarChart barChart;

    private float[] yData = {30, 40, 50, 60, 70};
    private String[] xData = {"A", "B", "C", "D", "E"};

    // TODO: Rename and change types and number of parameters
    public static FatsFragment newInstance(String param1, String param2) {
        FatsFragment fragment = new FatsFragment();
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
        // Inflate the layout for this
        View rootView = inflater.inflate(R.layout.fragment_fats, container, false);

        barChart = (BarChart) rootView.findViewById(R.id.bargraph);
        //tvX = (TextView) rootView.findViewById(R.id.tvXMax);
        //tvY = (TextView) rootView.findViewById(R.id.tvYMax);

        barChart.setDescription("Graph");
        addDataSet();

        //ArrayList<String> thevalues = new ArrayList<>();
        //.add("Vitamin A");
        ////thevalues.add("Vitamin B");
        //thevalues.add("Vitamin C");
        //thevalues.add("Vitamin D");

        //BarDataset theData = new BarDataset(thevalues, "Values");

        //ArrayList<BarEntry> barEntries = new ArrayList<>();
        //barEntries.add(new BarEntry(44f,0));
        //barEntries.add(new BarEntry(88f,1));
        //barEntries.add(new BarEntry(32f,2));
        //barEntries.add(new BarEntry(56f,3));

        //BarData barDataSet = new BarData();

        //barChart.setOnChartValueSelectedListener(this);
        //barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleXEnabled(true);
        barChart.setScaleYEnabled(true);


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

    private void addDataSet()
    {
        ArrayList<BarEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++)
        {
            yEntrys.add(new BarEntry(yData[i], i));
        }

        for(int i = 0; i < xData.length; i++)
        {
            xEntrys.add(xData[i]);
        }

        //create the data set
        BarDataSet pieDataSet = new BarDataSet(yEntrys, "Nutrition");
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
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setCustom(ColorTemplate.JOYFUL_COLORS, xData);


        //create pie data object
        BarData pieData = new BarData(pieDataSet);
        barChart.setData(pieData);
        barChart.invalidate();


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
