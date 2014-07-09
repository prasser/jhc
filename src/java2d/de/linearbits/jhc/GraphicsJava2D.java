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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;

/**
 * This class implements a graphics object for Java2D
 * 
 * @author Fabian Prasser
 */
class GraphicsJava2D implements Graphics<Image, Color> {

    /** The antialias. */
    private Object          antialias;

    /** The component. */
    private Component       component;

    /** The gc. */
    private Graphics2D      gc;

    /** The interpolation. */
    private Object          interpolation;

    /** The transformation. */
    private AffineTransform transformation;

    /**
     * Instantiates a new graphics java2 d.
     * 
     * @param component the component
     * @param g the g
     */
    protected GraphicsJava2D(Component component, Graphics2D g) {
        this.component = component;
        this.gc = g;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#disableAntialiasing()
     */
    @Override
    public void disableAntialiasing() {
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#disableInterpolation()
     */
    @Override
    public void disableInterpolation() {
        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawImage(java.lang.Object, int, int, int, int)
     */
    @Override
    public void drawImage(Image image, int x, int y, int width, int height) {
        gc.drawImage(image, x, y, width, height, component);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawLegend(de.linearbits.jhc.Gradient)
     */
    @Override
    public Image drawLegend(JHCGradient gradient) {
        PixelsJava2D pixels = new PixelsJava2D(new Dimension(1, gradient.getSteps()), gradient.getColor(0));
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
        gc.drawRect(x, y, width, height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawRectangleFilled(int, int, int, int)
     */
    @Override
    public void drawRectangleFilled(int x, int y, int width, int height) {
        Color temp = gc.getColor();
        gc.setColor(gc.getBackground());
        gc.fillRect(x, y, width, height);
        gc.setColor(temp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringAboveHorizontallyCentered(java.lang.String, int, int, int)
     */
    @Override
    public void drawStringAboveHorizontallyCentered(String string, int x, int y, int width) {

        int extentY = getTextHeight(string);
        drawStringBelowHorizontallyCentered(string, x, y - extentY, width);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringBelowHorizontallyCentered(java.lang.String, int, int, int)
     */
    @Override
    public void drawStringBelowHorizontallyCentered(String string, int x, int y, int width) {

        int extentX = getTextWidth(string);
        int extentY = getTextHeight(string);
        int offsetY = getTextOffset(string);

        gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        if (width >= extentX) {

            gc.setClip(x, y, width, extentY);
            int xx = x + (width - extentX) / 2;
            gc.drawString(string, xx, y + offsetY);
        } else {
            int postfixWidth = getTextWidth(POSTFIX);
            gc.setClip(x, y, width - postfixWidth, extentY);
            gc.drawString(string, x, y + offsetY);
            gc.setClip(x + width - postfixWidth, y, postfixWidth, extentY);
            gc.drawString(POSTFIX, x + width - postfixWidth, y + offsetY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#drawStringVerticallyCenteredLeftAligned(java.lang.String, int, int, int, int)
     */
    @Override
    public void drawStringVerticallyCenteredLeftAligned(String string, int x, int y, int width, int height) {

        int extentY = getTextHeight(string);
        int extentX = getTextWidth(string);
        int offsetY = getTextOffset(string);

        int yy = y + (height - extentY) / 2;

        gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        if (width >= extentX) {
            gc.setClip(x, yy, extentX, extentY);
            gc.drawString(string, x, yy + offsetY);
        } else {
            int postfixWidth = getTextWidth(POSTFIX);
            gc.setClip(x, yy, width - postfixWidth, extentY);
            gc.drawString(string, x, yy + offsetY);
            gc.setClip(x + width - postfixWidth, yy, postfixWidth, extentY);
            gc.drawString(POSTFIX, x + width - postfixWidth, yy + offsetY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#enableAntialiasing()
     */
    @Override
    public void enableAntialiasing() {
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#enableInterpolation()
     */
    @Override
    public void enableInterpolation() {
        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#getTextHeight(java.lang.String)
     */
    @Override
    public int getTextHeight(String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        LineMetrics lm = gc.getFont().getLineMetrics(string, frc);
        return Math.round(lm.getDescent() + lm.getAscent());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#getTextWidth(java.lang.String)
     */
    @Override
    public int getTextWidth(String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        return (int) Math.round(gc.getFont().getStringBounds(string, frc).getWidth());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#init()
     */
    @Override
    public void init() {
        gc.setFont(component.getFont());
        antialias = gc.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        interpolation = gc.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
        if (interpolation == null) {
            interpolation = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetAntialiasing()
     */
    @Override
    public void resetAntialiasing() {
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetClipping()
     */
    @Override
    public void resetClipping() {
        gc.setClip(0, 0, component.getWidth(), component.getHeight());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetInterpolation()
     */
    @Override
    public void resetInterpolation() {
        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#resetRotation()
     */
    @Override
    public void resetRotation() {
        gc.setTransform(transformation);
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
        gc.setColor(color);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Graphics#setRotation(int)
     */
    @Override
    public void setRotation(int degrees) {

        transformation = gc.getTransform();
        gc.rotate(Math.toRadians(degrees));
    }

    /**
     * Gets the text offset.
     * 
     * @param string the string
     * @return the text offset
     */
    private int getTextOffset(String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        LineMetrics lm = gc.getFont().getLineMetrics(string, frc);
        return Math.round(lm.getAscent());
    }

    @Override
    public void drawStringCentered(String string, int x, int y, int width, int height) {

        int extentX = getTextWidth(string);
        int extentY = getTextHeight(string);
        int offsetY = getTextOffset(string);
        gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        int xx = x + (width - extentX) / 2;
        int yy = y + (height - extentY) / 2;
        gc.setClip(xx, yy, extentX, extentY);
        gc.drawString(string, xx, yy + offsetY);
    }
}
