package org.thosp.mpchartexample.custom;

import org.thosp.charting.components.AxisBase;
import org.thosp.charting.formatter.IAxisValueFormatter;

/**
 
 */
@SuppressWarnings("unused")
public class YearXAxisFormatter implements IAxisValueFormatter
{

    private final String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    public YearXAxisFormatter() {
        // take parameters to change behavior of formatter
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        float percent = value / axis.mAxisRange;
        return mMonths[(int) (mMonths.length * percent)];
    }
}
