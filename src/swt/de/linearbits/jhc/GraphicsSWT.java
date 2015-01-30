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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;

/**
 * This class implements graphics operations for SWT
 * 
 * @author Fabian Prasser
 */
class GraphicsSWT implements Graphics<Image, Color> {

    /** The antialias. */
    private int       antialias;

    /** The canvas. */
    private CanvasSWT canvas;

    /** The gc. */
    private GC        gc;

    /** The interpolation. */
    private int       interpolation;

    /**
     * Creates a new instance
     * 
     * @param canvas the canvas
     * @param gc the gc
     */
    protected GraphicsSWT(CanvasSWT canvas, GC gc) {
        this.canvas = canvas;
        this.gc = gc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#disableAntialiasing()
     */
    @Override
    public void disableAntialiasing() {
        gc.setAntialias(SWT.OFF);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#disableInterpolation()
     */
    @Override
    public void disableInterpolation() {
        gc.setInterpolation(SWT.NONE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawImage(java.lang.Object, int, int, int, int)
     */
    @Override
    public void drawImage(Image image, int x, int y, int width, int height) {
        org.eclipse.swt.graphics.Rectangle bounds = image.getBounds();
        gc.drawImage(image, 0, 0, bounds.width, bounds.height, x, y, width, height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawLegend(de.linearbits.jhc.Gradient)
     */
    @Override
    public Image drawLegend(JHCGradient gradient) {
        PixelsSWT pixels = new PixelsSWT(new Dimension(1, gradient.getSteps()), canvas.getDisplay(), gradient.getColor(0));
        for (int i = 0; i < gradient.getSteps(); i++) {
            pixels.set(0, i, gradient.getColor(i));
        }
        pixels.update();
        return pixels.getImage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawLine(int, int, int, int)
     */
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        gc.drawLine(x1, y1, x2, y2);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawRectangle(int, int, int, int)
     */
    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        gc.drawRectangle(x, y, width, height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawRectangleFilled(int, int, int, int)
     */
    @Override
    public void drawRectangleFilled(int x, int y, int width, int height) {
        gc.fillRectangle(x, y, width, height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringAboveHorizontallyCentered(java.lang.String, int, int, int)
     */
    @Override
    public void drawStringAboveHorizontallyCentered(String string, int x, int y, int width) {

        Point extent = gc.textExtent(string);
        int yy = y - extent.y;
        if (width >= extent.x) {
            gc.setClipping(x, yy, width, extent.y);
            int xx = x + (width - extent.x) / 2;
            gc.drawText(string, xx, yy, true);
        } else {
            int postfixWidth = gc.textExtent(POSTFIX).x;
            gc.setClipping(x, yy, width - postfixWidth, extent.y);
            gc.drawText(string, x, yy, true);
            gc.setClipping(x + width - postfixWidth, yy, postfixWidth, extent.y);
            gc.drawText(POSTFIX, x + width - postfixWidth, yy, true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringBelowHorizontallyCentered(java.lang.String, int, int, int)
     */
    @Override
    public void drawStringBelowHorizontallyCentered(String string, int x, int y, int width) {
        Point extent = gc.textExtent(string);
        int descent = gc.getFontMetrics().getDescent();
        if (width >= extent.x) {
            gc.setClipping(x, y + descent, width, extent.y);
            int xx = x + (width - extent.x) / 2;
            gc.drawText(string, xx, y + descent, true);
        } else {
            int postfixWidth = gc.textExtent(POSTFIX).x;
            gc.setClipping(x, y + descent, width - postfixWidth, extent.y);
            gc.drawText(string, x, y + descent, true);
            gc.setClipping(x + width - postfixWidth, y + descent, postfixWidth, extent.y);
            gc.drawText(POSTFIX, x + width - postfixWidth, y + descent, true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringVerticallyCenteredLeftAligned(java.lang.String, int, int, int, int)
     */
    @Override
    public void drawStringVerticallyCenteredLeftAligned(String string, int x, int y, int width, int height) {

        Point extent = gc.textExtent(string);
        int yy = y + height / 2 - extent.y / 2;

        if (width >= extent.x) {
            gc.setClipping(x, yy, width, extent.y);
            gc.drawText(string, x, yy, true);
        } else {
            int postfixWidth = gc.textExtent(POSTFIX).x;
            gc.setClipping(x, yy, width - postfixWidth, extent.y);
            gc.drawText(string, x, yy, true);
            gc.setClipping(x + width - postfixWidth, yy, postfixWidth, extent.y);
            gc.drawText(POSTFIX, x + width - postfixWidth, yy, true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#enableAntialiasing()
     */
    @Override
    public void enableAntialiasing() {
        gc.setAntialias(SWT.ON);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#enableInterpolation()
     */
    @Override
    public void enableInterpolation() {
        gc.setInterpolation(SWT.HIGH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#getTextHeight(java.lang.String)
     */
    @Override
    public int getTextHeight(String string) {
        return gc.textExtent(string).y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#getTextWidth(java.lang.String)
     */
    @Override
    public int getTextWidth(String string) {
        return gc.textExtent(string).x;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#init()
     */
    @Override
    public void init() {
        gc.setFont(canvas.getFont());
        antialias = gc.getAntialias();
        interpolation = gc.getInterpolation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetAntialiasing()
     */
    @Override
    public void resetAntialiasing() {
        gc.setAntialias(antialias);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetClipping()
     */
    @Override
    public void resetClipping() {
        org.eclipse.swt.graphics.Rectangle bounds = canvas.getBounds();
        gc.setClipping(0, 0, bounds.width, bounds.height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetInterpolation()
     */
    @Override
    public void resetInterpolation() {
        gc.setInterpolation(interpolation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetRotation()
     */
    @Override
    public void resetRotation() {
        gc.setTransform(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#setBackground(java.lang.Object)
     */
    @Override
    public void setBackground(Color color) {
        gc.setBackground(color);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#setForeground(java.lang.Object)
     */
    @Override
    public void setForeground(Color color) {
        gc.setForeground(color);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#setRotation(int)
     */
    @Override
    public void setRotation(int degrees) {
        Transform tr = new Transform(canvas.getDisplay());
        tr.rotate(degrees);
        gc.setTransform(tr);
    }

    @Override
    public void drawStringCentered(String string, int x, int y, int width, int height) {

        Point extent = gc.textExtent(string);
        int yy = y + (height - extent.y) / 2;
        int xx = x + (width - extent.x) / 2;
        gc.setClipping(xx, yy, extent.x, extent.y);
        gc.drawText(string, xx, yy, true);
    }
}
