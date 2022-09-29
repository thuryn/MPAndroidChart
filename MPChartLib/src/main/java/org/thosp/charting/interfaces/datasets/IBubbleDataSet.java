package org.thosp.charting.interfaces.datasets;

import org.thosp.charting.data.BubbleEntry;

/**
 
 */
public interface IBubbleDataSet extends IBarLineScatterCandleBubbleDataSet<BubbleEntry> {

    /**
     * Sets the width of the circle that surrounds the bubble when highlighted,
     * in dp.
     *
     * @param width
     */
    void setHighlightCircleWidth(float width);

    float getMaxSize();

    boolean isNormalizeSizeEnabled();

    /**
     * Returns the width of the highlight-circle that surrounds the bubble
      * @return
     */
    float getHighlightCircleWidth();
}
