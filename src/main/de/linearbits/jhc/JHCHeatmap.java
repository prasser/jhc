/*
 * JHC - Java Heatmap Control
 * Copyright (C) 2014 Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
