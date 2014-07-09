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

import java.awt.Image;

/**
 * This class implements JHC for AWT
 * 
 * @author Fabian Prasser
 * 
 */
class _JHC extends CanvasAWT {
    
    /** The Constant serialVersionUID. */
    private static final long        serialVersionUID = 6100042540949178860L;

    /** The config. */
    private volatile JHCConfiguration   config           = null;

    /** The data. */
    private volatile JHCData            data             = null;

    /** The renderer. */
    private volatile Renderer<Image>  renderer         = new RendererJava2D(this, this);

    /**
     * Creates a new instance
     */
    _JHC() {
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

    /*
     * Updates the data to be rendered
     **/
    protected void setData(JHCData data, JHCConfiguration config) {
        this.data = data;
        this.config = config;
        this.renderer.render(data, config);
    }
}
