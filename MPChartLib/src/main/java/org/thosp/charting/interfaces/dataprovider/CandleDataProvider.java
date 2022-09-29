package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
