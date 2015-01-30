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

import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * This class implements JHC for SWT
 * 
 * @author Fabian Prasser
 */
class _JHC extends CanvasSWT {

    /** The config. */
    private JHCConfiguration                config   = null;

    /** The data. */
    private JHCData                         data     = null;

    /** The renderer. */
    private Renderer<Image>                 renderer = new RendererSWT(this, this);

    /** The listeners */
    private List<SelectionListener> listeners;

    /**
     * Creates a new instance
     * 
     * @param parent the parent
     * @param style the style
     * @param listeners 
     */
    _JHC(Composite parent, int style, List<SelectionListener> listeners) {
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
        this.listeners = listeners;
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
     * Fires selection events
     */
    protected void fireSelectionEvent() {
        Event untypedEvent = new Event();
        untypedEvent.display = this.getDisplay();
        untypedEvent.item = this;
        untypedEvent.widget = this;
        SelectionEvent event = new SelectionEvent(untypedEvent);
        for (SelectionListener listener : listeners) {
            listener.widgetSelected(event);
        }
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
