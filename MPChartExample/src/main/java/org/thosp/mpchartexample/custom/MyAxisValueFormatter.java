package org.thosp.mpchartexample.custom;

import org.thosp.charting.components.AxisBase;
import org.thosp.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private final DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " $";
    }
}
