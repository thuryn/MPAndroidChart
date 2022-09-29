package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.components.YAxis.AxisDependency;
import org.thosp.charting.data.BarLineScatterCandleBubbleData;
import org.thosp.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);

    Transformer getTransformer(int axis);
    boolean isInverted(int axis);

    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
