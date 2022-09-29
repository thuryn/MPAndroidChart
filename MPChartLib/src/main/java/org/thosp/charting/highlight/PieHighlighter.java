package org.thosp.charting.highlight;

import org.thosp.charting.charts.PieChart;
import org.thosp.charting.data.Entry;
import org.thosp.charting.interfaces.datasets.IPieDataSet;

/**
 
 */
public class PieHighlighter extends PieRadarHighlighter<PieChart> {

    public PieHighlighter(PieChart chart) {
        super(chart);
    }

    @Override
    protected Highlight getClosestHighlight(int index, float x, float y) {

        IPieDataSet set = mChart.getData().getDataSet();

        final Entry entry = set.getEntryForIndex(index);

        return new Highlight(index, entry.getY(), x, y, 0, set.getAxisDependency());
    }
}
