package com.ina.ina;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.renderer.Renderer;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MineralsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineralsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineralsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    BarChart barChart;

    float calcium = 0;
    float iron = 0;
    float magnesium = 0;
    float phosphorus = 0;
    float pottasium = 0;
    float sodium = 0;
    float zinc = 0;
    float copper = 0;
    float manganese = 0;
    float selenium = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MineralsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineralsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineralsFragment newInstance(String param1, String param2) {
        MineralsFragment fragment = new MineralsFragment();
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

        calcium = getArguments().getFloat("calcium");
        iron = getArguments().getFloat("iron");
        magnesium = getArguments().getFloat("magnesium");
        phosphorus = getArguments().getFloat("phosphorus");
        pottasium = getArguments().getFloat("potassium");
        sodium = getArguments().getFloat("sodium");
        zinc = getArguments().getFloat("zinc");
        copper = getArguments().getFloat("copper");
        manganese = getArguments().getFloat("manganese");
        selenium = getArguments().getFloat("selenium");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View rootView = inflater.inflate(R.layout.fragment_minerals, container, false);

        barChart = (BarChart) rootView.findViewById(R.id.bargraph);

        Legend l = barChart.getLegend();
        l.setEnabled(true);
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        l.setWordWrapEnabled(true);

        YAxis leftaxis = barChart.getAxisLeft();
        leftaxis.setSpaceTop(20f);
        YAxis rightaxis = barChart.getAxisRight();
        rightaxis.setSpaceTop(20f);

        barChart.getXAxis().setDrawLabels(false);

        //XAxis xaxis = barChart

        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(3f); // set the space between the legend entries on the y-axis

        // set custom labels and colors
        //change set names to be proper
        l.setCustom(new int[] {Color.rgb(255, 167, 38),
                Color.rgb(229, 57, 53), Color.rgb(244, 143, 177),
                Color.rgb(234, 128, 252), Color.rgb(140, 158, 255),
                Color.rgb(66, 165, 245), Color.rgb(24, 255, 255),
                Color.rgb(29, 233, 182), Color.rgb(76, 175, 80),
                Color.rgb(212, 225, 87)}, new String[] { "Calcium", "Iron", "Magnesium",
                "Phosphorous", "Potassium", "Sodium", "Zinc", "Copper", "Manganese", "Selenium" });

        //put them in like value is
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, calcium));
        entries.add(new BarEntry(1f, iron));
        entries.add(new BarEntry(2f, magnesium));
        entries.add(new BarEntry(3f, phosphorus));
        entries.add(new BarEntry(4f, pottasium));
        entries.add(new BarEntry(5f, sodium));
        entries.add(new BarEntry(6f, zinc));
        entries.add(new BarEntry(7f, copper));
        entries.add(new BarEntry(8f, manganese));
        entries.add(new BarEntry(9f, selenium));

        BarDataSet dataset = new BarDataSet(entries, "Calls");

        BarData data = new BarData(dataset);

        barChart.setData(data);
        barChart.setDescription("");
        dataset.setColors(new int[] {Color.rgb(255, 167, 38),
                Color.rgb(229, 57, 53), Color.rgb(244, 143, 177),
                Color.rgb(234, 128, 252), Color.rgb(140, 158, 255),
                Color.rgb(66, 165, 245), Color.rgb(24, 255, 255),
                Color.rgb(29, 233, 182), Color.rgb(76, 175, 80),
                Color.rgb(212, 225, 87)});
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
