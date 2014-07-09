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
import java.awt.Font;
import java.awt.Image;

/**
 * This class implements a renderer for Java2D
 * 
 * @author Fabian Prasser
 */
class RendererJava2D extends Renderer<Image> {

    /**
     * Instantiates a new renderer java2 d.
     * 
     * @param jhc the jhc
     * @param canvas the canvas
     */
    protected RendererJava2D(_JHC jhc, Canvas<Image, Font, Color> canvas) {
        super(jhc, canvas);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Renderer#getPixels(de.linearbits.jhc.Heat, de.linearbits.jhc.Configuration)
     */
    @Override
    public Pixels<Image> getPixels(JHCHeatmap heat, JHCConfiguration config) {
        int initialColor = config.getGradient().getColor(0);
        return new PixelsJava2D(new Dimension(heat.getWidth(), heat.getHeight()), initialColor);
    }
}
