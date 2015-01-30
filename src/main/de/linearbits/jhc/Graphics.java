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
 * This interface describes a generic graphics objects
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type of Image
 * @param <U> the generic type of Color
 */
interface Graphics<T, U> {

    /** The Constant POSTFIX. */
    public static final String POSTFIX = "...";

    /**
     * Disable antialiasing.
     */
    public void disableAntialiasing();

    /**
     * Disable interpolation.
     */
    public void disableInterpolation();

    /**
     * Draw image.
     * 
     * @param image the image
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public void drawImage(T image, int x, int y, int width, int height);

    /**
     * Draw legend.
     * 
     * @param gradient the gradient
     * @return the t
     */
    public T drawLegend(JHCGradient gradient);

    /**
     * Draw line.
     * 
     * @param x1 the x1
     * @param y1 the y1
     * @param x2 the x2
     * @param y2 the y2
     */
    public void drawLine(int x1, int y1, int x2, int y2);

    /**
     * Draw rectangle.
     * 
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public void drawRectangle(int x, int y, int width, int height);

    /**
     * Draw rectangle filled.
     * 
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public void drawRectangleFilled(int x, int y, int width, int height);

    /**
     * Draw string above horizontally centered.
     * 
     * @param string the string
     * @param x the x
     * @param y the y
     * @param width the width
     */
    public void drawStringAboveHorizontallyCentered(String string, int x, int y, int width);

    /**
     * Draw string below horizontally centered.
     * 
     * @param string the string
     * @param x the x
     * @param y the y
     * @param width the width
     */
    public void drawStringBelowHorizontallyCentered(String string, int x, int y, int width);

    /**
     * Draw string vertically centered left aligned.
     * 
     * @param string the string
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public void drawStringVerticallyCenteredLeftAligned(String string, int x, int y, int width, int height);

    /**
     * Enable antialiasing.
     */
    public void enableAntialiasing();

    /**
     * Enable interpolation.
     */
    public void enableInterpolation();

    /**
     * Gets the text height.
     * 
     * @param string the string
     * @return the text height
     */
    public int getTextHeight(String string);

    /**
     * Gets the text width.
     * 
     * @param string the string
     * @return the text width
     */
    public int getTextWidth(String string);

    /**
     * Inits the.
     */
    public void init();

    /**
     * Reset antialiasing.
     */
    public void resetAntialiasing();

    /**
     * Reset clipping.
     */
    public void resetClipping();

    /**
     * Reset interpolation.
     */
    public void resetInterpolation();

    /**
     * Reset rotation.
     */
    public void resetRotation();

    /**
     * Sets the background.
     * 
     * @param color the new background
     */
    public void setBackground(U color);

    /**
     * Sets the foreground.
     * 
     * @param color the new foreground
     */
    public void setForeground(U color);

    /**
     * Sets the rotation.
     * 
     * @param degrees the new rotation
     */
    public void setRotation(int degrees);

    /**
     * Draws the given string centered in the given rectangle
     * @param string
     * @param i
     * @param j
     * @param width
     * @param height
     */
    public void drawStringCentered(String string, int i, int j, int width, int height);
}
