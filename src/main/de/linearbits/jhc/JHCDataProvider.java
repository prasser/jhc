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


/**
 * This interface can be implemented to convert data into a representation suitable for the widget
 * 
 * @author Fabian Prasser
 */
public interface JHCDataProvider {
    
    /**
     * Data orientation
     * 
     * @author Fabian Prasser
     */
    public static enum Orientation {
        ROW,
        COLUMN
    }

    /**
     * Returns the.
     * 
     * @param x the x
     * @param y the y
     * @return the double
     */
    public abstract double get(int x, int y);

    /**
     * Gets the height.
     * 
     * @return the height
     */
    public abstract int getHeight();

    /**
     * Gets the width.
     * 
     * @return the width
     */
    public abstract int getWidth();

    /**
     * Gets the orientation.
     * 
     * @return the orientation
     */
    public abstract Orientation getOrientation();


    /**
     * Gets the x labels.
     * 
     * @return the x scale
     */
    public abstract JHCScale<?> getXScale();

    /**
     * Gets the y labels.
     * 
     * @return the y scale
     */
    public abstract JHCScale<?> getYScale();
}
