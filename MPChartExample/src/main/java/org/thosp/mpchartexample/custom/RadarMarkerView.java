
package org.thosp.mpchartexample.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import org.thosp.charting.components.MarkerView;
import org.thosp.charting.data.Entry;
import org.thosp.charting.highlight.Highlight;
import org.thosp.charting.utils.MPPointF;
import org.thosp.mpchartexample.R;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 *
 * 
 */
@SuppressLint("ViewConstructor")
public class RadarMarkerView extends MarkerView {

    private final TextView tvContent;
    private final DecimalFormat format = new DecimalFormat("##0");

    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
        tvContent.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(String.format("%s %%", format.format(e.getY())));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
