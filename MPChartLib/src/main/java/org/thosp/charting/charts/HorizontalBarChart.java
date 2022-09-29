package org.thosp.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import org.thosp.charting.components.XAxis.XAxisPosition;
import org.thosp.charting.components.YAxis.AxisDependency;
import org.thosp.charting.data.BarEntry;
import org.thosp.charting.data.Entry;
import org.thosp.charting.highlight.Highlight;
import org.thosp.charting.highlight.HorizontalBarHighlighter;
import org.thosp.charting.interfaces.datasets.IBarDataSet;
import org.thosp.charting.renderer.HorizontalBarChartRenderer;
import org.thosp.charting.renderer.XAxisRendererHorizontalBarChart;
import org.thosp.charting.renderer.YAxisRendererHorizontalBarChart;
import org.thosp.charting.utils.HorizontalViewPortHandler;
import org.thosp.charting.utils.MPPointF;
import org.thosp.charting.utils.TransformerHorizontalBarChart;
import org.thosp.charting.utils.Utils;

/**
 * BarChart with horizontal bar orientation. In this implementation, x- and y-axis are switched, meaning the YAxis class
 * represents the horizontal values and the XAxis class represents the vertical values.
 *
 * 
 */
public class HorizontalBarChart extends BarChart {

    public HorizontalBarChart(Context context) {
        super(context);
    }

    public HorizontalBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void init() {

        mViewPortHandler = new HorizontalViewPortHandler();

        super.init();

        axisTransformers[0] = new TransformerHorizontalBarChart(mViewPortHandler);
        axisTransformers[1] = new TransformerHorizontalBarChart(mViewPortHandler);

        mRenderer = new HorizontalBarChartRenderer(this, mAnimator, mViewPortHandler);
        setHighlighter(new HorizontalBarHighlighter(this));

        /*mAxisRendererLeft = new YAxisRendererHorizontalBarChart(mViewPortHandler, mAxisLeft, mLeftAxisTransformer);
        mAxisRendererRight = new YAxisRendererHorizontalBarChart(mViewPortHandler, mAxisRight, mRightAxisTransformer);
        mXAxisRenderer = new XAxisRendererHorizontalBarChart(mViewPortHandler, mXAxis, mLeftAxisTransformer, this);*/
    }

    private RectF mOffsetsBuffer = new RectF();

    protected void calculateLegendOffsets(RectF offsets) {

        offsets.left = 0.f;
        offsets.right = 0.f;
        offsets.top = 0.f;
        offsets.bottom = 0.f;

        if (mLegend == null || !mLegend.isEnabled() || mLegend.isDrawInsideEnabled())
            return;

        switch (mLegend.getOrientation()) {
            case VERTICAL:

                switch (mLegend.getHorizontalAlignment()) {
                    case LEFT:
                        offsets.left += Math.min(mLegend.mNeededWidth,
                                mViewPortHandler.getChartWidth() * mLegend.getMaxSizePercent())
                                + mLegend.getXOffset();
                        break;

                    case RIGHT:
                        offsets.right += Math.min(mLegend.mNeededWidth,
                                mViewPortHandler.getChartWidth() * mLegend.getMaxSizePercent())
                                + mLegend.getXOffset();
                        break;

                    case CENTER:

                        switch (mLegend.getVerticalAlignment()) {
                            case TOP:
                                offsets.top += Math.min(mLegend.mNeededHeight,
                                        mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent())
                                        + mLegend.getYOffset();
                                break;

                            case BOTTOM:
                                offsets.bottom += Math.min(mLegend.mNeededHeight,
                                        mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent())
                                        + mLegend.getYOffset();
                                break;

                            default:
                                break;
                        }
                }

                break;

            case HORIZONTAL:

                switch (mLegend.getVerticalAlignment()) {
                    case TOP:
                        offsets.top += Math.min(mLegend.mNeededHeight,
                                mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent())
                                + mLegend.getYOffset();

                        if (yAxes[0].isEnabled() && yAxes[0].isDrawLabelsEnabled())
                            offsets.top += yAxes[0].getRequiredHeightSpace(
                                    axisRenderers[0].getPaintAxisLabels());
                        break;

                    case BOTTOM:
                        offsets.bottom += Math.min(mLegend.mNeededHeight,
                                mViewPortHandler.getChartHeight() * mLegend.getMaxSizePercent())
                                + mLegend.getYOffset();

                        if (yAxes[1].isEnabled() && yAxes[1].isDrawLabelsEnabled())
                            offsets.bottom += yAxes[1].getRequiredHeightSpace(
                                    axisRenderers[1].getPaintAxisLabels());
                        break;

                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void calculateOffsets() {

        float offsetLeft = 0f, offsetRight = 0f, offsetTop = 0f, offsetBottom = 0f;

        calculateLegendOffsets(mOffsetsBuffer);

        offsetLeft += mOffsetsBuffer.left;
        offsetTop += mOffsetsBuffer.top;
        offsetRight += mOffsetsBuffer.right;
        offsetBottom += mOffsetsBuffer.bottom;

        // offsets for y-labels
        if (yAxes[0].needsOffset()) {
            offsetTop += yAxes[0].getRequiredHeightSpace(axisRenderers[0].getPaintAxisLabels());
        }

        if (yAxes[1].needsOffset()) {
            offsetBottom += yAxes[1].getRequiredHeightSpace(axisRenderers[1].getPaintAxisLabels());
        }

        float xlabelwidth = mXAxis.mLabelRotatedWidth;

        if (mXAxis.isEnabled()) {

            // offsets for x-labels
            if (mXAxis.getPosition() == XAxisPosition.BOTTOM) {

                offsetLeft += xlabelwidth;

            } else if (mXAxis.getPosition() == XAxisPosition.TOP) {

                offsetRight += xlabelwidth;

            } else if (mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {

                offsetLeft += xlabelwidth;
                offsetRight += xlabelwidth;
            }
        }

        offsetTop += getExtraTopOffset();
        offsetRight += getExtraRightOffset();
        offsetBottom += getExtraBottomOffset();
        offsetLeft += getExtraLeftOffset();

        float minOffset = Utils.convertDpToPixel(mMinOffset);

        mViewPortHandler.restrainViewPort(
                Math.max(minOffset, offsetLeft),
                Math.max(minOffset, offsetTop),
                Math.max(minOffset, offsetRight),
                Math.max(minOffset, offsetBottom));

        if (mLogEnabled) {
            Log.i(LOG_TAG, "offsetLeft: " + offsetLeft + ", offsetTop: " + offsetTop + ", offsetRight: " +
                    offsetRight + ", offsetBottom: "
                    + offsetBottom);
            Log.i(LOG_TAG, "Content: " + mViewPortHandler.getContentRect().toString());
        }

        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    @Override
    protected void prepareValuePxMatrix() {
        axisTransformers[1].prepareMatrixValuePx(yAxes[1].mAxisMinimum, yAxes[1].mAxisRange, mXAxis.mAxisRange,
                mXAxis.mAxisMinimum);
        axisTransformers[0].prepareMatrixValuePx(yAxes[0].mAxisMinimum, yAxes[0].mAxisRange, mXAxis.mAxisRange,
                mXAxis.mAxisMinimum);
    }

    @Override
    protected float[] getMarkerPosition(Highlight high) {
        return new float[]{high.getDrawY(), high.getDrawX()};
    }

    @Override
    public void getBarBounds(BarEntry e, RectF outputRect) {

        RectF bounds = outputRect;
        IBarDataSet set = mData.getDataSetForEntry(e);

        if (set == null) {
            outputRect.set(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
            return;
        }

        float y = e.getY();
        float x = e.getX();

        float barWidth = mData.getBarWidth();

        float top = x - barWidth / 2f;
        float bottom = x + barWidth / 2f;
        float left = y >= 0 ? y : 0;
        float right = y <= 0 ? y : 0;

        bounds.set(left, top, right, bottom);

        getTransformer(set.getAxisDependency()).rectValueToPixel(bounds);

    }

    protected float[] mGetPositionBuffer = new float[2];

    /**
     * Returns a recyclable MPPointF instance.
     *
     * @param e
     * @param axis
     * @return
     */
    @Override
    public MPPointF getPosition(Entry e, int axis) {

        if (e == null)
            return null;

        float[] vals = mGetPositionBuffer;
        vals[0] = e.getY();
        vals[1] = e.getX();

        getTransformer(axis).pointValuesToPixel(vals);

        return MPPointF.getInstance(vals[0], vals[1]);
    }

    /**
     * Returns the Highlight object (contains x-index and DataSet index) of the selected value at the given touch point
     * inside the BarChart.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public Highlight getHighlightByTouchPoint(float x, float y) {

        if (mData == null) {
            if (mLogEnabled)
                Log.e(LOG_TAG, "Can't select by touch. No data set.");
            return null;
        } else
            return getHighlighter().getHighlight(y, x); // switch x and y
    }

    @Override
    public float getLowestVisibleX() {
        getTransformer(0).getValuesByTouchPoint(mViewPortHandler.contentLeft(),
                mViewPortHandler.contentBottom(), posForGetLowestVisibleX);
        float result = (float) Math.max(mXAxis.mAxisMinimum, posForGetLowestVisibleX.y);
        return result;
    }

    @Override
    public float getHighestVisibleX() {
        getTransformer(0).getValuesByTouchPoint(mViewPortHandler.contentLeft(),
                mViewPortHandler.contentTop(), posForGetHighestVisibleX);
        float result = (float) Math.min(mXAxis.mAxisMaximum, posForGetHighestVisibleX.y);
        return result;
    }

    /**
     * ###### VIEWPORT METHODS BELOW THIS ######
     */

    @Override
    public void setVisibleXRangeMaximum(float maxXRange) {
        float xScale = mXAxis.mAxisRange / (maxXRange);
        mViewPortHandler.setMinimumScaleY(xScale);
    }

    @Override
    public void setVisibleXRangeMinimum(float minXRange) {
        float xScale = mXAxis.mAxisRange / (minXRange);
        mViewPortHandler.setMaximumScaleY(xScale);
    }

    @Override
    public void setVisibleXRange(float minXRange, float maxXRange) {
        float minScale = mXAxis.mAxisRange / minXRange;
        float maxScale = mXAxis.mAxisRange / maxXRange;
        mViewPortHandler.setMinMaxScaleY(minScale, maxScale);
    }

    @Override
    public void setVisibleYRangeMaximum(float maxYRange, AxisDependency axis) {
        int axisIndex = 0;
        if (AxisDependency.RIGHT == axis) {
            axisIndex = 1;
        }
        float yScale = getAxisRange(axisIndex) / maxYRange;
        mViewPortHandler.setMinimumScaleX(yScale);
    }

    @Override
    public void setVisibleYRangeMinimum(float minYRange, AxisDependency axis) {
        int axisIndex = 0;
        if (AxisDependency.RIGHT == axis) {
            axisIndex = 1;
        }
        float yScale = getAxisRange(axisIndex) / minYRange;
        mViewPortHandler.setMaximumScaleX(yScale);
    }

    @Override
    public void setVisibleYRange(float minYRange, float maxYRange, AxisDependency axis) {
        int axisIndex = 0;
        if (AxisDependency.RIGHT == axis) {
            axisIndex = 1;
        }
        float minScale = getAxisRange(axisIndex) / minYRange;
        float maxScale = getAxisRange(axisIndex) / maxYRange;
        mViewPortHandler.setMinMaxScaleX(minScale, maxScale);
    }
}
