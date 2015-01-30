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
