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
