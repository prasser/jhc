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

package de.linearbits.jhc;

import de.linearbits.jhc.JHCDataProvider.Orientation;

/**
 * This class defines the abstract base type for data that can be handled by the widget/control. Moreover, it offers
 * static methods for deriving instances for typical data representations, e.g., arrays. Extend this class, if you want to
 * encapsule potentially long-running processes that compute the heatmap
 * 
 * @author Fabian Prasser
 */
public abstract class JHCData {

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @return the data
     */
    public static JHCData create(double[][] data) {
        return new ProviderData(new ProviderDoubleArray(data, Orientation.ROW, null, null));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(double[][] data, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderDoubleArray(data, Orientation.ROW, xScale, yScale));
    }

    /**
     * Creates a data object
     * 
     * @param data the data
     * @param orientation the orientation
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(double[][] data, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderDoubleArray(data, orientation, xScale, yScale));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @return the data
     */
    public static JHCData create(char[][] data) {
        return new ProviderData(new ProviderCharArray(data, Orientation.ROW, null, null));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(char[][] data, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderCharArray(data, Orientation.ROW, xScale, yScale));
    }

    /**
     * Creates a data object
     * 
     * @param data the data
     * @param orientation the orientation
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(char[][] data, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderCharArray(data, orientation, xScale, yScale));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @return the data
     */
    public static JHCData create(int[][] data) {
        return new ProviderData(new ProviderIntegerArray(data, Orientation.ROW, null, null));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(int[][] data, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderIntegerArray(data, Orientation.ROW, xScale, yScale));
    }

    /**
     * Creates a data object
     * 
     * @param data the data
     * @param orientation the orientation
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(int[][] data, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderIntegerArray(data, orientation, xScale, yScale));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @return the data
     */
    public static JHCData create(long[][] data) {
        return new ProviderData(new ProviderLongArray(data, Orientation.ROW, null, null));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(long[][] data, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderLongArray(data, Orientation.ROW, xScale, yScale));
    }

    /**
     * Creates a data object
     * 
     * @param data the data
     * @param orientation the orientation
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(long[][] data, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderLongArray(data, orientation, xScale, yScale));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @return the data
     */
    public static JHCData create(float[][] data) {
        return new ProviderData(new ProviderFloatArray(data, Orientation.ROW, null, null));
    }

    /**
     * Creates a data object. Assumes a row-oriented layout.
     * 
     * @param data the data
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(float[][] data, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderFloatArray(data, Orientation.ROW, xScale, yScale));
    }

    /**
     * Creates a data object
     * 
     * @param data the data
     * @param orientation the orientation
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the data
     */
    public static JHCData create(float[][] data, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale) {
        return new ProviderData(new ProviderFloatArray(data, orientation, xScale, yScale));
    }
    /**
     * Creates the.
     * 
     * @param provider the provider
     * @return the data
     */
    public static JHCData create(JHCDataProvider provider) {
        return new ProviderData(provider);
    }

    /**
     * Gets the heat.
     * 
     * @param width the width
     * @param height the height
     * @return the heat
     */
    public abstract JHCHeatmap getHeatmap(int width, int height);

    /**
     * Gets the original width of the data
     * 
     * @return the width
     */
    public abstract int getWidth();

    /**
     * Gets the original height of the data
     * 
     * @return the height
     */
    public abstract int getHeight();
}
