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

import java.awt.Image;

/**
 * This class implements JHC for Swing
 * 
 * @author Fabian Prasser
 */
class _JHC extends CanvasSwing {

    /** The Constant serialVersionUID. */
    private static final long         serialVersionUID = 6100042540949178860L;

    /** The config. */
    private volatile JHCConfiguration config           = null;

    /** The data. */
    private volatile JHCData          data             = null;

    /** The renderer. */
    private volatile Renderer<Image>  renderer         = new RendererJava2D(this, this);

    /**
     * Creates a new instance
     */
    protected _JHC() {
        super.setListener(new CanvasListener(){
            @Override
            public void resized() {
                _JHC.this.resized();
            }

            @Override
            public void shown() {
                _JHC.this.shown();
            }
        });
    }

    /*
     * @see de.linearbits.jhc.CanvasListener#resized()
     */
    private void resized() {
        this.renderer.render(data, config);
    }

    /*
     * @see de.linearbits.jhc.CanvasListener#shown()
     */
    private void shown() {
        this.renderer.render(data, config);
    }

    /**
     * Sets the data to be rendered
     */
    protected void setData(JHCData data, JHCConfiguration config) {
        this.data = data;
        this.config = config;
        this.renderer.render(data, config);
    }
}
