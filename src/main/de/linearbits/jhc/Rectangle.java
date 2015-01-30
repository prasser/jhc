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
 * This class implements a rectangle
 * 
 * @author Fabian Prasser
 */
class Rectangle {

    /** The height. */
    public final int height;

    /** The width. */
    public final int width;

    /** The x. */
    public final int x;

    /** The y. */
    public final int y;

    /**
     * Instantiates a new rectangle.
     */
    protected Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Instantiates a new rectangle.
     * 
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    protected Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Rectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
}
