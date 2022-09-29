package org.thosp.mpchartexample.custom;

import org.thosp.charting.data.Entry;
import org.thosp.charting.formatter.IValueFormatter;
import org.thosp.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements IValueFormatter
{

    private final DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + " $";
    }
}
