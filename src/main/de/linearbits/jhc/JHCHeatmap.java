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

import java.util.Iterator;

/**
 * This abstract class represents a (potentially scaled) heatmap that has been retrieved from an instance of JHCData
 * 
 * @author Fabian Prasser
 */
public abstract class JHCHeatmap {
    
    /**
     * This interface represents a point in the heatmap. It is defined by indices on the x- and y-axis where each
     * index corresponds to the according entry in the current scale.
     * 
     * @author Fabian Prasser
     */
    public static interface Point {

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public abstract double getValue();

        /**
         * Gets the x.
         * 
         * @return the x
         */
        public abstract int getX();

        /**
         * Gets the y.
         * 
         * @return the y
         */
        public abstract int getY();
    }
    
    /**
     * Gets the height.
     * 
     * @return the height
     */
    public abstract int getHeight();

    /**
     * Gets the max.
     * 
     * @return the max
     */
    public abstract double getMax();

    /**
     * Gets the min.
     * 
     * @return the min
     */
    public abstract double getMin();

    /**
     * Gets the width.
     * 
     * @return the width
     */
    public abstract int getWidth();

    /**
     * Gets the x label.
     * 
     * @param index the index
     * @return the x label
     */
    public abstract String getXLabel(int index);

    /**
     * Gets the y label.
     * 
     * @param index the index
     * @return the y label
     */
    public abstract String getYLabel(int index);

    /**
     * Iterator.
     * 
     * @return the iterator
     */
    public abstract Iterator<Point> iterator();
}
