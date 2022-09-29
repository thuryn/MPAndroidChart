package org.thosp.charting.interfaces.datasets;

import org.thosp.charting.data.Entry;

/**
 
 */
public interface IBarLineScatterCandleBubbleDataSet<T extends Entry> extends IDataSet<T> {

    /**
     * Returns the color that is used for drawing the highlight indicators.
     *
     * @return
     */
    int getHighLightColor();
}
