package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.data.CombinedData;

/**
 
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
