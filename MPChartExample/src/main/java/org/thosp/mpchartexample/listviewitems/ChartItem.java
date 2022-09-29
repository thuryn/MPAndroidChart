package org.thosp.mpchartexample.listviewitems;

import android.content.Context;
import android.view.View;

import org.thosp.charting.data.ChartData;

/**
 * Base class of the Chart ListView items
 
 *
 */
@SuppressWarnings("unused")
public abstract class ChartItem {

    static final int TYPE_BARCHART = 0;
    static final int TYPE_LINECHART = 1;
    static final int TYPE_PIECHART = 2;

    ChartData<?> mChartData;

    ChartItem(ChartData<?> cd) {
        this.mChartData = cd;
    }

    public abstract int getItemType();

    public abstract View getView(int position, View convertView, Context c);
}
