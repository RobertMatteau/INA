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
import com.github.mikephil.charting.components.YAxis;
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

    float fat;

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

        fat = getArguments().getFloat("fats");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View rootView = inflater.inflate(R.layout.fragment_fats, container, false);

        barChart = (BarChart) rootView.findViewById(R.id.bargraph);

        Legend l = barChart.getLegend();
        l.setEnabled(true);
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(3f); // set the space between the legend entries on the y-axis

        l.setWordWrapEnabled(true);

        YAxis leftaxis = barChart.getAxisLeft();
        leftaxis.setSpaceTop(20f);
        YAxis rightaxis = barChart.getAxisRight();
        rightaxis.setSpaceTop(20f);

        barChart.getXAxis().setDrawLabels(false);

        // set custom labels and colors
        //change set names to be proper
        l.setCustom(new int[] {Color.rgb(255, 167, 38)}, new String[] { "Fats" });

        //put them in like value is
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1f, fat));

        BarDataSet dataset = new BarDataSet(entries, "Calls");

        BarData data = new BarData(dataset);

        barChart.setData(data);
        barChart.setDescription("");
        dataset.setColors(new int[] {Color.rgb(255, 167, 38)});
        barChart.animateY(5000);

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
