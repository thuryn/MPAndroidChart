package org.thosp.charting.interfaces.dataprovider;

import org.thosp.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
