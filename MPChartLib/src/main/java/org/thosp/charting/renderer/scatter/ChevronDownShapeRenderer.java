package org.thosp.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.thosp.charting.interfaces.datasets.IScatterDataSet;
import org.thosp.charting.utils.Utils;
import org.thosp.charting.utils.ViewPortHandler;

/**
 * Created by wajdic on 15/06/2016.
 * Created at Time 09:08
 */
public class ChevronDownShapeRenderer implements IShapeRenderer
{


    @Override
    public void renderShape(Canvas c, IScatterDataSet dataSet, ViewPortHandler viewPortHandler,
                     float posX, float posY, Paint renderPaint) {

        final float shapeHalf = dataSet.getScatterShapeSize() / 2f;

        renderPaint.setStyle(Paint.Style.STROKE);
        renderPaint.setStrokeWidth(Utils.convertDpToPixel(1f));

        c.drawLine(
                posX,
                posY + (2 * shapeHalf),
                posX + (2 * shapeHalf),
                posY,
                renderPaint);

        c.drawLine(
                posX,
                posY + (2 * shapeHalf),
                posX - (2 * shapeHalf),
                posY,
                renderPaint);
    }
}
