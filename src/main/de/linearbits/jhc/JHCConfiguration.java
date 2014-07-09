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
 * This class allows to configure how the heatmap is displayed.
 * 
 * @author Fabian Prasser
 */
public class JHCConfiguration {

    /** The gradient. */
    private final JHCGradient gradient;

    /** The layout. */
    private final JHCLayout   layout;

    /** The maximal size. */
    private final Dimension   maximalSize;

    /** The x label. */
    private final String      xLabel;

    /** The y label. */
    private final String      yLabel;

    /**
     * Instantiates a new configuration.
     * 
     * @param xLabel the x label
     * @param yLabel the y label
     * @param maximalWidth the maximal width
     * @param maximalHeight the maximal height
     * @param gradient the gradient
     */
    public JHCConfiguration(String xLabel, String yLabel, int maximalWidth, int maximalHeight, JHCGradient gradient) {
        this(xLabel, yLabel, maximalWidth, maximalHeight, gradient, null);
    }

    /**
     * Instantiates a new configuration.
     * 
     * @param xLabel the x label
     * @param yLabel the y label
     * @param maximalSize the maximal size
     * @param gradient the gradient
     * @param layout the layout
     */
    public JHCConfiguration(String xLabel, String yLabel, int maximalWidth, int maximalHeight, JHCGradient gradient, JHCLayout layout) {
        this.maximalSize = new Dimension(maximalWidth, maximalHeight);
        this.gradient = gradient;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        if (layout == null) {
            this.layout = new JHCLayout();
        } else {
            this.layout = layout;
        }
    }

    /**
     * Instantiates a new configuration.
     * 
     * @param xLabel the x label
     * @param yLabel the y label
     * @param gradient the gradient
     */
    public JHCConfiguration(String xLabel, String yLabel, JHCGradient gradient) {
        this(xLabel, yLabel, Integer.MAX_VALUE, Integer.MAX_VALUE, gradient, null);
    }

    /**
     * Instantiates a new configuration.
     * 
     * @param xLabel the x label
     * @param yLabel the y label
     * @param gradient the gradient
     * @param layout the layout
     */
    public JHCConfiguration(String xLabel, String yLabel, JHCGradient gradient, JHCLayout layout) {
        this(xLabel, yLabel, Integer.MAX_VALUE, Integer.MAX_VALUE, gradient, layout);
    }

    /**
     * Gets the gradient.
     * 
     * @return the gradient
     */
    protected JHCGradient getGradient() {
        return gradient;
    }

    /**
     * Gets the layout.
     * 
     * @return the layout
     */
    protected JHCLayout getLayout() {
        return layout;
    }

    /**
     * Gets the maximal size.
     * 
     * @return the maximal size
     */
    protected Dimension getMaximalSize() {
        return maximalSize;
    }

    /**
     * Gets the x label.
     * 
     * @return the x label
     */
    protected String getXLabel() {
        return xLabel;
    }

    /**
     * Gets the y label.
     * 
     * @return the y label
     */
    protected String getYLabel() {
        return yLabel;
    }
}
