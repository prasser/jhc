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

import de.linearbits.jhc.JHCDataProvider.Orientation;
import de.linearbits.jhc.JHCHeatmap.Point;

/**
 * This class implements JHC data based on a given data provider
 * 
 * @author Fabian Prasser
 */
class ProviderData extends JHCData {
	
    /**
     * The Class HeatPointProvider.
     */
    protected class ProviderDataPoint implements Point {

        /** The x */
        protected int x;

        /** The y */
        protected int y;

        /** The value*/
        protected double value;
        
        @Override
        public double getValue() {
            return value;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
    }

    /** The x labels. */
    protected final JHCScale<?> xScale;

    /** The y labels. */
    protected final JHCScale<?> yScale;
    
    /** The orientation*/
    private final Orientation orientation;
    
    /** The provider*/
    private final JHCDataProvider provider;

    /** The min*/
    private double min = Double.MAX_VALUE;

    /** The max*/
    private double max = -Double.MAX_VALUE;
    
    /**
     * Instantiates a new data provider.
     * 
     * @param provider the provider
     */
    protected ProviderData(JHCDataProvider provider) {

        // Check
        if (provider == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        if (provider.getWidth() == 0 || provider.getHeight() == 0) {
            throw new IllegalArgumentException("Data must not be empty");
        }

        // Init
        this.xScale = provider.getXScale();
        this.yScale = provider.getYScale();
        this.orientation = provider.getOrientation();
        this.provider = provider;

        if (xScale != null) {
            xScale.check(provider.getWidth());
        }
        if (yScale != null) {
            yScale.check(provider.getHeight());
        }
    }

    /**
     * Check if all required values have been computed
     */
    private void ensureMinMaxAvailable() {
        if (min==Double.MAX_VALUE && max==-Double.MAX_VALUE) {
           
            switch (orientation){
            case COLUMN:
                for (int x = 0; x < provider.getWidth(); x++) {
                    for (int y = 0; y < provider.getHeight(); y++) {
                        double value = provider.get(x, y);
                        min = Math.min(min, value);
                        max = Math.max(max, value);
                    }
                }
                break;
            case ROW:
                for (int y = 0; y < provider.getHeight(); y++) {
                    for (int x = 0; x < provider.getWidth(); x++) {
                        double value = provider.get(x, y);
                        min = Math.min(min, value);
                        max = Math.max(max, value);
                    }
                }
                break;
            }
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Data#getHeat(int, int)
     */
    @Override
    public JHCHeatmap getHeatmap(int width, int height) {

        int originalWidth = provider.getWidth();
        int originalHeight = provider.getHeight();

        // Check bounds
        if (width > originalWidth) width = originalWidth;
        if (height > originalHeight) height = originalHeight;

        // Scale the scale
        JHCScale<?> xScale = this.xScale != null ? this.xScale.adjust(width) : null;
        JHCScale<?> yScale = this.yScale != null ? this.yScale.adjust(height) : null;

        // Check if no need to scale
        if (width == originalWidth && height == originalHeight) {
            ensureMinMaxAvailable();
            ProviderDataPoint point = new ProviderDataPoint();
            return getHeatmap(point, provider, min, max, xScale, yScale);
        }

        // Create maps
        int[] xMap = createMap(width, originalWidth);
        int[] yMap = createMap(height, originalHeight);

        // Create entry set
        double[][] frequencies = new double[height][width];
        int[][] counts = new int[height][width];
        for (int y = 0; y < height; y++) {
            frequencies[y] = new double[width];
            counts[y] = new int[width];
        }
        
        if (min == Double.MAX_VALUE && max == -Double.MAX_VALUE) {
            
            switch (orientation){
            case COLUMN:
                for (int x = 0; x < provider.getWidth(); x++) {
                    int xIndex = xMap[x];
                    for (int y = 0; y < provider.getHeight(); y++) {
                        int yIndex = yMap[y];
                        double value = provider.get(x,y);
                        frequencies[yIndex][xIndex] += value;
                        counts[yIndex][xIndex]++;
                        max = Math.max(max, value);
                        min = Math.min(min, value);
                    }
                }
                break;
            case ROW:
                for (int y = 0; y < provider.getHeight(); y++) {
                    int yIndex = yMap[y];
                    for (int x = 0; x < provider.getWidth(); x++) {
                        int xIndex = xMap[x];
                        double value = provider.get(x,y);
                        frequencies[yIndex][xIndex] += value;
                        counts[yIndex][xIndex]++;
                        max = Math.max(max, value);
                        min = Math.min(min, value);
                    }
                }
                break;
            }     
        } else {
            
            switch (orientation){
            case COLUMN:
                for (int x = 0; x < provider.getWidth(); x++) {
                    int xIndex = xMap[x];
                    for (int y = 0; y < provider.getHeight(); y++) {
                        int yIndex = yMap[y];
                        frequencies[yIndex][xIndex] += provider.get(x,y);
                        counts[yIndex][xIndex]++;
                    }
                }
                break;
            case ROW:
                for (int y = 0; y < provider.getHeight(); y++) {
                    int yIndex = yMap[y];
                    for (int x = 0; x < provider.getWidth(); x++) {
                        int xIndex = xMap[x];
                        frequencies[yIndex][xIndex] += provider.get(x,y);
                        counts[yIndex][xIndex]++;
                    }
                }
                break;
            }
            
        }
        
        // Aggregate
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                frequencies[y][x] = frequencies[y][x] / counts[y][x];
            }
        }

        // Return
        ProviderDataPoint point = new ProviderDataPoint();
        return getHeatmap(point, width, height, originalWidth, originalHeight, frequencies, min, max, xScale, yScale);
    }

    @Override
    public int getWidth() {
        return provider.getWidth();
    }

    @Override
    public int getHeight() {
        return provider.getHeight();
    }

    /**
     * Creates the index.
     * 
     * @param target the target
     * @param source the source
     * @param index the index
     * @return the int
     */
    private int createIndex(int target, int source, int index) {
        index = (int) Math.round((double) target / (double) source * index);
        return index < target ? index : target - 1;
    }

    /**
     * Creates the map.
     * 
     * @param target the target
     * @param source the source
     * @return the int[]
     */
    protected int[] createMap(int target, int source) {
        int[] result = new int[source];
        if (source != target) {
            for (int x = 0; x < source; x++) {
                result[x] = createIndex(target, source, x);
            }
        } else {
            for (int x = 0; x < source; x++) {
                result[x] = x;
            }
        }
        return result;
    }

    /**
     * Gets the heat.
     * 
     * @param point the point
     * @param width the width
     * @param height the height
     * @param originalWidth the original width
     * @param originalHeight the original height
     * @param max the max
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the heat
     */
    protected JHCHeatmap getHeatmap(final ProviderDataPoint point, final int width, final int height, final int originalWidth, final int originalHeight, final double[][] data, final double min, final double max, final JHCScale<?> xScale, final JHCScale<?> yScale) {

        // Create iterator
        final Iterator<Point> iterator = new Iterator<Point>() {

            int x = 0;
            int y = 0;

            @Override
            public boolean hasNext() {
                return y < height;
            }

            @Override
            public Point next() {

                point.x = x;
                point.y = y;
                point.value = data[y][x];

                x++;
                if (x == width) {
                    x = 0;
                    y++;
                }

                return point;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        // Return heat
        return new JHCHeatmap() {

            @Override
            public int getHeight() {
                return height;
            }

            @Override
            public double getMin() {
                return min;
            }
            
            @Override
            public double getMax() {
                return max;
            }

            @Override
            public int getWidth() {
                return width;
            }

            @Override
            public String getXLabel(int index) {
                if (xScale == null) {
                    if (originalWidth != width) {
                        int lower = (int) ((double) index / (double) width * originalWidth);
                        int upper = (int) ((double) (index + 1) / (double) width * originalWidth);
                        if (upper > originalWidth) upper = originalWidth;
                        return "[" + lower + ", " + upper + "[";

                    } else {
                        return String.valueOf(index);
                    }
                } else return xScale.getLabelAt(index);
            }

            @Override
            public String getYLabel(int index) {
                if (yScale == null) {
                    if (originalHeight != height) {
                        int lower = (int) ((double) index / (double) height * originalHeight);
                        int upper = (int) ((double) (index + 1) / (double) height * originalHeight);
                        if (upper > originalHeight) upper = originalHeight;
                        return "[" + lower + ", " + upper + "[";

                    } else {
                        return String.valueOf(index);
                    }
                } else return yScale.getLabelAt(index);
            }

            @Override
            public Iterator<Point> iterator() {
                return iterator;
            }
        };
    }
    
    /**
     * Gets the heat for an unscaled version of the provider's data
     * 
     * @param point the point
     * @param provider the provider
     * @param min the min
     * @param max the max
     * @param xScale the x scale
     * @param yScale the y scale
     * @return the heat
     */
    protected JHCHeatmap getHeatmap(final ProviderDataPoint point, final JHCDataProvider provider, final double min, final double max, final JHCScale<?> xScale, final JHCScale<?> yScale) {


        final int width = provider.getWidth();
        final int height = provider.getHeight();
        
        // Create iterator
        final Iterator<Point> iterator = new Iterator<Point>() {

            int x = 0;
            int y = 0;

            @Override
            public boolean hasNext() {
                return y < height;
            }

            @Override
            public Point next() {

                point.x = x;
                point.y = y;
                point.value = provider.get(x, y);

                x++;
                if (x == width) {
                    x = 0;
                    y++;
                }

                return point;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        // Return heat
        return new JHCHeatmap() {

            @Override
            public int getHeight() {
                return height;
            }

            @Override
            public double getMin() {
                return min;
            }

            @Override
            public double getMax() {
                return max;
            }

            @Override
            public int getWidth() {
                return width;
            }

            @Override
            public String getXLabel(int index) {
                if (xScale == null) {
                    return String.valueOf(index);
                } else {
                    return xScale.getLabelAt(index);
                }
            }

            @Override
            public String getYLabel(int index) {
                if (yScale == null) {
                    return String.valueOf(index);
                } else {
                    return yScale.getLabelAt(index);
                }
            }

            @Override
            public Iterator<Point> iterator() {
                return iterator;
            }
        };
    }
}
