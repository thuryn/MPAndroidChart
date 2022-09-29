package org.thosp.mpchartexample.custom;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.thosp.charting.interfaces.datasets.IScatterDataSet;
import org.thosp.charting.renderer.scatter.IShapeRenderer;
import org.thosp.charting.utils.ViewPortHandler;

/**
 * Custom shape renderer that draws a single line.
 
 */
public class CustomScatterShapeRenderer implements IShapeRenderer
{

    @Override
    public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler,
                            float posX, float posY, Paint renderPaint) {

        final float shapeHalf = dataSet.getScatterShapeSize() / 2f;

        c.drawLine(
                posX - shapeHalf,
                posY - shapeHalf,
                posX + shapeHalf,
                posY + shapeHalf,
                renderPaint);
    }
}
