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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * This class implements JHC for SWT
 * 
 * @author Fabian Prasser
 */
class _JHC extends CanvasSWT {

    /** Resize delay */
    public static final int           RESIZE_DELAY = 500;

    /** The config. */
    private volatile JHCConfiguration config       = null;

    /** The data. */
    private volatile JHCData          data         = null;

    /** The renderer. */
    private volatile Renderer<Image>  renderer     = new RendererSWT(this, this);


    /**
     * Creates a new instance
     * 
     * @param parent the parent
     * @param style the style
     */
    _JHC(Composite parent, int style) {
        super(parent, style);
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
     * Sets new data to render
     */
    protected void setData(JHCData data, JHCConfiguration config) {
        checkWidget();
        this.data = data;
        this.config = config;
        this.renderer.render(data, config);
    }
}
