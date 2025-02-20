
package org.thosp.charting.renderer;

import org.thosp.charting.utils.ViewPortHandler;

/**
 * Abstract baseclass of all Renderers.
 * 
 * 
 */
public abstract class Renderer {

    /**
     * the component that handles the drawing area of the chart and it's offsets
     */
    protected ViewPortHandler mViewPortHandler;

    public Renderer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }
}
