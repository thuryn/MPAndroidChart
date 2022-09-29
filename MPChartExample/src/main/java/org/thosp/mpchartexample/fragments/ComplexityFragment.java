package org.thosp.mpchartexample.fragments;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.thosp.charting.charts.LineChart;
import org.thosp.charting.components.Legend;
import org.thosp.charting.components.XAxis;
import org.thosp.charting.components.YAxis;
import org.thosp.mpchartexample.R;


public class ComplexityFragment extends SimpleFragment {

    @NonNull
    public static Fragment newInstance() {
        return new ComplexityFragment();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private LineChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_line, container, false);

        chart = v.findViewById(R.id.lineChart1);

        chart.getDescription().setEnabled(false);

        chart.setDrawGridBackground(false);

        chart.setData(getComplexity());
        chart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        Legend l = chart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        return v;
    }
}
