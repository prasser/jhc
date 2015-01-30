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
