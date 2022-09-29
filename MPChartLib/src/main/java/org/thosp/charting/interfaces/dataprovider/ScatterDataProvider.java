package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
