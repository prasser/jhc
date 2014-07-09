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
 * This interface describes an array of pixels
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 */
interface Pixels<T> {

    /**
     * Gets the image.
     * 
     * @return the image
     */
    public abstract T getImage();

    /**
     * Gets the size.
     * 
     * @return the size
     */
    public abstract Dimension getSize();

    /**
     * Sets the.
     * 
     * @param x the x
     * @param y the y
     * @param val the val
     */
    public abstract void set(int x, int y, int val);

    /**
     * Update.
     */
    public abstract void update();
}
