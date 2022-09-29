package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.components.YAxis;
import org.thosp.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
