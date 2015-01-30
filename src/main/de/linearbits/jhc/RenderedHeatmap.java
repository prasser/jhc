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

/**
 * This class represents a heatmap that has been rendered to an image
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 */
class RenderedHeatmap<T> {
    
    @SuppressWarnings("rawtypes")
    protected static final RenderedHeatmap<?> NO_DATA = new RenderedHeatmap();
    @SuppressWarnings("rawtypes")
    protected static final RenderedHeatmap<?> TOO_SMALL = new RenderedHeatmap();

    /** The config. */
    private final JHCConfiguration config;

    /** The heat. */
    private final JHCHeatmap       heat;

    /** The height. */
    private final int              height;

    /** The image. */
    private final T                image;

    /** The width. */
    private final int              width;

    /**
     * Creates a new instance
     * 
     * @param image the image
     * @param heat the heat
     * @param config the config
     */
    protected RenderedHeatmap(T image, JHCHeatmap heat, JHCConfiguration config) {
        this.image = image;
        this.heat = heat;
        this.config = config;
        this.width = heat.getWidth();
        this.height = heat.getHeight();
    }

    /** 
     * For special instances
     */
    private RenderedHeatmap() {
        this.image = null;
        this.heat = null;
        this.config = null;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Gets the config.
     * 
     * @return the config
     */
    protected JHCConfiguration getConfig() {
        return config;
    }

    /**
     * Gets the height.
     * 
     * @return the height
     */
    protected int getHeight() {
        return height;
    }

    /**
     * Gets the image.
     * 
     * @return the image
     */
    protected T getImage() {
        return image;
    }

    /**
     * Gets the width.
     * 
     * @return the width
     */
    protected int getWidth() {
        return width;
    }

    /**
     * Gets the x label.
     * 
     * @param index the index
     * @return the x label
     */
    protected String getXLabel(int index) {
        if (heat == null) return null;
        else return heat.getXLabel(index);
    }

    /**
     * Gets the y label.
     * 
     * @param index the index
     * @return the y label
     */
    protected String getYLabel(int index) {
        if (heat == null) return null;
        else return heat.getYLabel(index);
    }
    
    /**
     * Returns the min
     * @return
     */
    protected double getMin(){
        if (heat == null) return 0;
        else return heat.getMin();
    }
    
    /**
     * Returns the max
     */
    protected double getMax() {
        if (heat == null) return 0;
        else return heat.getMax();
    }
}
