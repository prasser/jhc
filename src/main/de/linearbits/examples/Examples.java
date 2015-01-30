/* ******************************************************************************
 * Copyright (c) 2014 - 2015 Fabian Prasser.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Fabian Prasser - initial API and implementation
 ******************************************************************************/

package de.linearbits.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;
import de.linearbits.jhc.JHCGradient;
import de.linearbits.jhc.JHCLayout;
import de.linearbits.jhc.JHCScale;

/**
 * This class implements basic examples
 * 
 * @author Fabian Prasser
 */
class Examples extends ExampleData {

    /**
     * Example1.
     * 
     * @param window the window
     */
    protected static void example1(Window<?, ?> window) {

        JHCData data = JHCData.create(getArray3());
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_HEAT);
        window.open(data, config);
    }

    /**
     * Example2.
     * 
     * @param window the window
     */
    protected static void example2(Window<?, ?> window) {

        JHCData data = JHCData.create(getArray2());
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_BLACKBODY);
        window.open(data, config);
    }

    /**
     * Example3.
     * 
     * @param window the window
     */
    protected static void example3(Window<?, ?> window) {

        JHCData data = JHCData.create(getArray2());
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", 30, 30, JHCGradient.GRADIENT_BLUERED);
        window.open(data, config);
    }

    /**
     * Example4.
     * 
     * @param window the window
     */
    protected static void example4(Window<?, ?> window) {
        JHCData data = JHCData.create(getArray3(), new JHCScale.Decimal(10, 30), null);
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_ELECTRIC);
        window.open(data, config);
    }

    /**
     * Example5.
     * 
     * @param window the window
     */
    protected static void example5(Window<?, ?> window) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2010, 5, 1);
        Date from = cal.getTime();
        cal.set(2015, 5, 1);
        Date to = cal.getTime();
        JHCScale<Date> dateScale = new JHCScale.Date(from, to, new SimpleDateFormat("dd.MM.yyy"));

        JHCData data = JHCData.create(getArray8(), new JHCScale.Decimal(10, 30), dateScale);
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_GREENS);
        window.open(data, config);
    }

    /**
     * Example6.
     * 
     * @param window the window
     */
    protected static void example6(Window<?, ?> window) {

        JHCScale<String> xScale = new JHCScale.String(new String[] { "A", "AB", "C", "ABCDE", "ZZZZZZZZZZZZZZZZZZZZZZZZZ" });
        JHCScale<String> yScale = new JHCScale.String(new String[] { "A", "AB", "C", "ABCDE", "ZZZZZZZZZZZZZZZZZZZZZZZZZ" });

        JHCLayout layout = new JHCLayout(true, 100, true, 100);
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_HEAT, layout);

        JHCData data = JHCData.create(getArray9(), xScale, yScale);
        window.open(data, config);
    }

    /**
     * Example7.
     * 
     * @param window the window
     */
    protected static void example7(Window<?, ?> window) {

        String[] ticks = new String[100];
        for (int i = 0; i < 100; i++)
            ticks[i] = "Tick-" + i;

        JHCScale<String> xScale = new JHCScale.String(ticks);
        JHCScale<String> yScale = new JHCScale.String(ticks);

        JHCLayout layout = new JHCLayout(true, 100, true, 100);
        JHCConfiguration config = new JHCConfiguration("x-label", "y-label", JHCGradient.GRADIENT_YIGNBN, layout);

        JHCData data = JHCData.create(getArray10(), xScale, yScale);
        window.open(data, config);
    }
}
