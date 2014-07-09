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
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * The class implements gradients for the heatmap. Some predefined gradients are
 * inspired by plotly (https://github.com/plotly). 
 * 
 * @author Fabian Prasser
 */
public class JHCGradient {

    /** BLACK, GRAY, WHITE */
    public static final JHCGradient GRADIENT_GRAYSCALE = new JHCGradient(new Color[] 
            { Color.BLACK, Color.GRAY, Color.WHITE });

    /**  BLUE, CYAN, GREEN, YELLOW, ORANGE, RED */
    public static final JHCGradient GRADIENT_HEAT      = new JHCGradient(new Color[] 
            { Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED });

    /** BLACK, LIGHT_GRAY*/
    public static final JHCGradient GRADIENT_GRAYS     = new JHCGradient(new Color[]
            {Color.BLACK, Color.LIGHT_GRAY});
    
    /** WHITE, GREEN */
    public static final JHCGradient GRADIENT_YIGNBN     = new JHCGradient(new Color[]
            {Color.WHITE, Color.GREEN});
    
    /** DARK_GREEN, LIGHT_GREEN */
    public static final JHCGradient GRADIENT_GREENS     = new JHCGradient(new Color[]
            {Color.decode("#013220"), Color.decode("#90ee90")});
    
    /** RED, ORANGE, GOLD, TAN, WHITE*/
    public static final JHCGradient GRADIENT_YIORRD     = new JHCGradient(new Color[]
            {Color.RED, Color.ORANGE, Color.decode("#ffd700"), Color.decode("#d2b48c"), Color.WHITE});

    /** BRIGHT_BLUE, PURPLE, BRIGHT_RED*/
    public static final JHCGradient GRADIENT_BLUERED     = new JHCGradient(new Color[]
            {Color.decode("#00a1c2"), Color.decode("#800080"), Color.decode("#be0032")});

    /** BLUE, RED*/
    public static final JHCGradient GRADIENT_RDBU     = new JHCGradient(new Color[]
            {Color.BLUE, Color.RED});

    /** BLUE, LIGHT_BLUE, WHITE, PINK*/
    public static final JHCGradient GRADIENT_PICNIC     = new JHCGradient(new Color[]
            {Color.BLUE, Color.decode("#add8e6"), Color.WHITE, Color.PINK});

    /** BLUE, GREEN, YELLOW, ORANGE, RED*/
    public static final JHCGradient GRADIENT_PORTLAND     = new JHCGradient(new Color[]
            {Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED});

    /** BLUE, LIGHT_BLUE, GREEN, YELLOW, ORANGE, RED*/
    public static final JHCGradient GRADIENT_JET     = new JHCGradient(new Color[]
            {Color.BLUE, Color.decode("#add8e6"), Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED});
       
    /** TAN, YELLOW, RED, BLACK*/
    public static final JHCGradient GRADIENT_HOT     = new JHCGradient(new Color[]
            {Color.decode("#d2b48c"), Color.YELLOW, Color.RED, Color.BLACK});

    /** BLACK, RED, YELLOW, WHITE, LIGHT_BLUE*/
    public static final JHCGradient GRADIENT_BLACKBODY     = new JHCGradient(new Color[]
            {Color.BLACK, Color.RED, Color.YELLOW, Color.WHITE, Color.decode("#add8e6")});

    /** BLUE, GREEN, YELLOW, BROWN, TAN, WHITE*/
    public static final JHCGradient GRADIENT_EARTH     = new JHCGradient(new Color[]
            {Color.BLUE, Color.GREEN, Color.YELLOW, Color.decode("#a52a2a"), Color.decode("#d2b48c"), Color.WHITE});

    /** BLACK, PURPLE, ORANGE, YELLOW, TAN, WHITE*/
    public static final JHCGradient GRADIENT_ELECTRIC     = new JHCGradient(new Color[]
            {Color.BLACK, Color.decode("#800080"), Color.ORANGE, Color.YELLOW, Color.decode("#d2b48c"), Color.WHITE});
       
    /** The colors. */
    private int[]                colors;

    /**
     * Instantiates a new gradient.
     * 
     * @param _colors the _colors
     */
    public JHCGradient(Color[] _colors) {
        this(_colors, 100);
    }

    /**
     * Instantiates a new gradient.
     * 
     * @param _colors the _colors
     * @param steps the steps
     */
    public JHCGradient(Color[] _colors, int steps) {
        this.colors = getGradient(_colors, steps);
    }

    /**
     * Gets the color.
     * 
     * @param heat the heat
     * @return the color
     */
    protected int getColor(int heat) {
        return colors[heat];
    }

    /**
     * Gets the steps.
     * 
     * @return the steps
     */
    protected int getSteps() {
        return colors.length;
    }

    /**
     * Gets the gradient.
     * 
     * @param colors the colors
     * @param steps the steps
     * @return the gradient
     */
    private int[] getGradient(Color[] colors, int steps) {

        // Draw the gradient to a buffered image
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(1, steps);
        float[] dist = new float[colors.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = (1.0f / dist.length) * i;
        }
        LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors);
        BufferedImage legend = new BufferedImage(1, steps, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) legend.getGraphics();
        g2d.setPaint(p);
        g2d.drawRect(0, 0, 1, steps);
        g2d.dispose();

        // Convert to color array
        int[] result = new int[steps];
        for (int y = 0; y < steps; y++) {
            result[y] = legend.getRGB(0, y);
        }

        // Return
        return result;
    }
}
