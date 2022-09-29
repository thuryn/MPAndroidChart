package org.thosp.mpchartexample.custom;

import org.thosp.charting.formatter.IFillFormatter;
import org.thosp.charting.interfaces.dataprovider.LineDataProvider;
import org.thosp.charting.interfaces.datasets.ILineDataSet;

/**
 
 */
@SuppressWarnings("unused")
public class MyFillFormatter implements IFillFormatter
{

    private float fillPos;

    public MyFillFormatter(float fillPos) {
        this.fillPos = fillPos;
    }

    @Override
    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        // your logic could be here
        return fillPos;
    }
}
