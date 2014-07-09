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
 * This interface describes a generic canvas for JHC
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 * @param <U> the generic type
 * @param <V> the value type
 */
interface Canvas<T, U, V> {
    
    /**
     * The listener interface for receiving canvas events.
     * The class that is interested in processing a canvas
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addCanvasListener<code> method. When
     * the canvas event occurs, that object's appropriate
     * method is invoked.
     * 
     * @author Fabian Prasser
     */
    interface CanvasListener {

        /**
         * Resized.
         */
        public void resized();

        /**
         * Shown.
         */
        public void shown();
    }


    /**
     * Gets the background.
     * 
     * @return the background
     */
    public V getBackground();

    /**
     * Gets the foreground.
     * 
     * @return the foreground
     */
    public V getForeground();

    /**
     * Gets the black.
     * 
     * @return the black
     */
    public V getBlack();

    /**
     * Gets the canvas size.
     * 
     * @return the canvas size
     */
    public abstract Dimension getCanvasSize();

    /**
     * Gets the extents.
     * 
     * @return the extents
     */
    public abstract Dimension getExtents();

    /**
     * Gets the font.
     * 
     * @return the font
     */
    public U getFont();

    /**
     * Gets the gray.
     * 
     * @return the gray
     */
    public V getGray();

    /**
     * Sets the heatmap.
     * 
     * @param heatmap the new heatmap
     */
    public abstract void setHeatmap(RenderedHeatmap<?> heatmap);

    /**
     * Sets the listener.
     * 
     * @param listener the new listener
     */
    public void setListener(CanvasListener listener);
}
