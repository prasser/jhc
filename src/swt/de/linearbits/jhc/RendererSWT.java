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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;


/**
 * This class implements a renderer for SWT
 * 
 * @author Fabian Prasser
 */
class RendererSWT extends Renderer<Image> {

    /** The display. */
    private final Display display;

    /**
     * Creates a new instance
     * 
     * @param jhc the jhc
     * @param canvas the canvas
     */
    protected RendererSWT(_JHC jhc, Canvas<Image, Font, Color> canvas) {
        super(jhc, canvas);
        this.display = jhc.getDisplay();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Renderer#getPixels(de.linearbits.jhc.Heat, de.linearbits.jhc.Configuration)
     */
    @Override
    protected Pixels<Image> getPixels(JHCHeatmap heat, JHCConfiguration config) {
        int initialColor = config.getGradient().getColor(0);
        return new PixelsSWT(new Dimension(heat.getWidth(), heat.getHeight()), display, initialColor);
    }
}
