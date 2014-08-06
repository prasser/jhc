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
