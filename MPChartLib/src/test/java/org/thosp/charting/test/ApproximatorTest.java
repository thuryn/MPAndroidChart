package org.thosp.charting.test;

import org.thosp.charting.data.Entry;
import org.thosp.charting.data.filter.Approximator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 
 */
public class ApproximatorTest {

    @Test
    public void testApproximation() {

        float[] points = new float[]{
                10, 20,
                20, 30,
                25, 25,
                30, 28,
                31, 31,
                33, 33,
                40, 40,
                44, 40,
                48, 23,
                50, 20,
                55, 20,
                60, 25};

        assertEquals(24, points.length);

        Approximator a = new Approximator();

        float[] reduced = a.reduceWithDouglasPeucker(points, 2);

        assertEquals(18, reduced.length);
    }
}
