/*
 * JHC - Java Heatmap Control
 * Copyright (C) 2014 Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General protected License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General protected License for more details.
 * 
 * You should have received a copy of the GNU General protected License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.linearbits.jhc;

/**
 * This class represents a layout that has been rendered in a concrete context
 * 
 * @author Fabian Prasser
 */
class RenderedLayout {

    /** The draw x ticks. */
    private final boolean drawXTicks;

    /** The draw y labels. */
    private final boolean drawYTicks;

    /** The heat height. */
    private final int     heatHeight;

    /** The heat width. */
    private final int     heatWidth;

    /** The height. */
    private final int     height;

    /** The layout. */
    private final JHCLayout  layout;

    /** The text offset. */
    private final int     textOffset;

    /** The width. */
    private final int     width;

    /** The x. */
    private final int     x;

    /** The x labels rotate. */
    private final boolean rotateXTicks;

    /** The x labels rotate space. */
    private final int     xTicksSpace;

    /** The x tick offset. */
    private final double  xTicksOffset;

    /** The y. */
    private final int     y;

    /** The y labels rotate. */
    private final boolean rotateYTicks;

    /** The y labels rotate space. */
    private final int     yTicksSpace;

    /** The y tick offset. */
    private final double  yTicksOffset;

    /**
     * Instantiates a new layout rendered.
     * 
     * @param layout the layout
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param heatWidth the heat width
     * @param heatHeight the heat height
     * @param textOffset the text offset
     * @param xTickOffset the x tick offset
     * @param yTickOffset the y tick offset
     * @param drawXTicks the draw x labels
     * @param drawYTicks the draw y labels
     * @param rotateXTicks the x labels rotate
     * @param xTicksSpace the x labels rotate space
     * @param rotateYTicks the y labels rotate
     * @param yTicksSpace the y labels rotate space
     */
    protected RenderedLayout(JHCLayout layout, int x, int y, int width, int height, int heatWidth, int heatHeight, int textOffset, double xTickOffset, double yTickOffset, boolean drawXTicks, boolean drawYTicks, boolean rotateXTicks, int xTicksSpace, boolean rotateYTicks, int yTicksSpace) {

        this.layout = layout;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textOffset = textOffset;
        this.xTicksOffset = xTickOffset;
        this.yTicksOffset = yTickOffset;
        this.drawXTicks = drawXTicks;
        this.drawYTicks = drawYTicks;
        this.heatHeight = heatHeight;
        this.heatWidth = heatWidth;
        this.rotateXTicks = rotateXTicks;
        this.xTicksSpace = xTicksSpace;
        this.rotateYTicks = rotateYTicks;
        this.yTicksSpace = yTicksSpace;
    }

    /**
     * Gets the center offset.
     * 
     * @return the center offset
     */
    protected int getCenterOffset() {
        return layout.getCenterOffset();
    }

    /**
     * Gets the heat height.
     * 
     * @return the heat height
     */
    protected int getHeatHeight() {
        return heatHeight;
    }

    /**
     * Gets the heat width.
     * 
     * @return the heat width
     */
    protected int getHeatWidth() {
        return heatWidth;
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
     * Gets the large tick length.
     * 
     * @return the large tick length
     */
    protected int getLargeTickLength() {
        return layout.getLargeTickLength();
    }

    /**
     * Gets the legend width.
     * 
     * @return the legend width
     */
    protected int getLegendWidth() {
        return layout.getLegendWidth();
    }

    /**
     * Gets the small tick length.
     * 
     * @return the small tick length
     */
    protected int getSmallTickLength() {
        return layout.getSmallTickLength();
    }

    /**
     * Gets the text offset.
     * 
     * @return the text offset
     */
    protected int getTextOffset() {
        return textOffset;
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
     * Gets the x.
     * 
     * @return the x
     */
    protected int getX() {
        return x;
    }

    /**
     * Gets the x labels rotate space.
     * 
     * @return the x labels rotate space
     */
    protected int getXTicksSpace() {
        return xTicksSpace;
    }

    /**
     * Gets the x tick offset.
     * 
     * @return the x tick offset
     */
    protected double getxTickOffset() {
        return xTicksOffset;
    }

    /**
     * Gets the y.
     * 
     * @return the y
     */
    protected int getY() {
        return y;
    }

    /**
     * Gets the y labels rotate space.
     * 
     * @return the y labels rotate space
     */
    protected int getYTicksSpace() {
        return yTicksSpace;
    }

    /**
     * Gets the y tick offset.
     * 
     * @return the y tick offset
     */
    protected double getyTickOffset() {
        return yTicksOffset;
    }

    /**
     * Checks if is draw x labels.
     * 
     * @return true, if is draw x labels
     */
    protected boolean isDrawXTicks() {
        return drawXTicks;
    }

    /**
     * Checks if is draw y labels.
     * 
     * @return true, if is draw y labels
     */
    protected boolean isDrawYTicks() {
        return drawYTicks;
    }

    /**
     * Checks if is x labels rotate.
     * 
     * @return true, if is x labels rotate
     */
    protected boolean isXTicksRotate() {
        return rotateXTicks;
    }

    /**
     * Checks if is y labels rotate.
     * 
     * @return true, if is y labels rotate
     */
    protected boolean isYTicksRotate() {
        return rotateYTicks;
    }
}
