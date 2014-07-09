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
 * This class implements a data provider based on double arrays
 * 
 * @author Fabian Prasser
 */
class ProviderDoubleArray implements JHCDataProvider{
    
    /** Orientation*/
    private final Orientation orientation;
    /** Array*/
    private final double[][] array;
    /** Scale*/
    private final JHCScale<?> xScale;
    /** Scale*/
    private final JHCScale<?> yScale;

    /**
     * Creates a new instance
     * @param array
     */
    protected ProviderDoubleArray(double[][] array){
        this(array, Orientation.ROW, null, null);
    }
        
    /**
     * Creates a new instance
     * @param array
     * @param orientation
     */
    protected ProviderDoubleArray(double[][] array, Orientation orientation){
        this(array, orientation, null, null);
    }
        
    /**
     * Creates a new instance
     * @param array
     * @param orientation
     * @param xScale
     * @param yScale
     */
    protected ProviderDoubleArray(double[][] array, Orientation orientation, JHCScale<?> xScale, JHCScale<?> yScale){
        
        if (array == null || array.length == 0 || array[0] == null || array[0].length ==0){
            throw new IllegalArgumentException("Array must not be empty");
        }
        
        int length = -1;
        for (double[] row : array) {
            if (row == null || row.length == 0){
                throw new IllegalArgumentException("Array must not be empty");    
            }
            if (length == -1) {
                length = row.length;
            } else if (length != row.length) {
                throw new IllegalArgumentException("All rows must have equal length");
            }
        }
        
        if (orientation == null) {
            throw new IllegalArgumentException("Orientation must not be null");
        }

        this.xScale = xScale;
        this.yScale = yScale;
        this.array = array;
        this.orientation = orientation;
    }

    /**
     * Returns the.
     * 
     * @param x the x
     * @param y the y
     * @return the double
     */
    public double get(int x, int y) {
        switch (orientation){
        case ROW:
            return array[y][x];
        case COLUMN:
            return array[x][y];
        }
        throw new IllegalStateException("Unknown array orientation");
    }

    /**
     * Gets the height.
     * 
     * @return the height
     */
    public int getHeight() {
        switch (orientation){
        case ROW:
            return array.length;
        case COLUMN:
            return array[0].length;
        }
        throw new IllegalStateException("Unknown array orientation");
    }

    /**
     * Gets the width.
     * 
     * @return the width
     */
    public int getWidth() {
        switch (orientation){
        case ROW:
            return array[0].length;
        case COLUMN:
            return array.length;
        }
        throw new IllegalStateException("Unknown array orientation");
    }

    /**
     * Gets the x labels.
     * 
     * @return the x scale
     */
    public JHCScale<?> getXScale() {
        return xScale;
    }

    /**
     * Gets the y labels.
     * 
     * @return the y scale
     */
    public JHCScale<?> getYScale() {
        return yScale;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }
}
