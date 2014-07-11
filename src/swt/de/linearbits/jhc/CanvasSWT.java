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

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * This class implements a canvas for SWT
 * 
 * @author Fabian Prasser
 */
class CanvasSWT extends org.eclipse.swt.widgets.Canvas implements Canvas<Image, Font, Color> {

    /** The Constant SHOWN_DELAY. */
    private static final int            SHOWN_DELAY = 200;

    /** The black. */
    private Color                       black       = null;

    /** The gray. */
    private Color                       gray        = null;

    /** The heatmap. */
    private RenderedHeatmap<Image>      heatmap     = null;

    /** The listener. */
    private CanvasListener              listener    = null;

    /** The painter. */
    private Painter<Image, Font, Color> painter     = null;

    /** The size. */
    private Point                       size        = null;

    /** The text extents. */
    private Dimension                   textExtents = null;

    /** The tool tip*/
    private ToolTip                     tooltip     = new ToolTip();

    /**
     * Creates a new instance
     * 
     * @param parent the parent
     * @param style the style
     */
    protected CanvasSWT(Composite parent, int style) {

        super(parent, style);
        
        this.black = new Color(getDisplay(), 0, 0, 0);
        this.gray = new Color(getDisplay(), 128, 128, 128);
        this.textExtents = getTextExtents();
        this.setCanvasSize(getSize());
        this.painter = new PainterSWT<Image, Font, Color>(this);
        
        this.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent arg0) {
                painter.dispose();
                black.dispose();
                gray.dispose();
            }
        });

        this.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent arg0) {
                setCanvasSize(getSize());
                if (listener != null) listener.resized();
            }
        });

        // Simulate shown event
        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent arg0) {
                removePaintListener(this);
                getDisplay().timerExec(SHOWN_DELAY, new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) listener.shown();
                    }
                });
            }
        });

        // Paint the component
        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent arg0) {
                if (painter != null) {
                    Dimension size = getCanvasSize();
                    GraphicsSWT graphics = new GraphicsSWT(CanvasSWT.this, arg0.gc);
                    painter.paint(graphics, heatmap, size);
                }
            }
        });

        this.addMouseMoveListener(new MouseMoveListener() {
            @Override
            public void mouseMove(MouseEvent arg0) {
                if (heatmap != null && painter != null) {
                    String text = tooltip.getToolTip(heatmap, painter, arg0.x, arg0.y, false, true);
                    CanvasSWT.this.setToolTipText(text);
                } else {
                    CanvasSWT.this.setToolTipText(null);
                }
            }
        });
    }

    @Override
    public Color getBlack() {
        return black;
    }

    @Override
    public Dimension getCanvasSize() {
        return new Dimension(size.x, size.y);
    }

    @Override
    public Dimension getExtents() {
        return this.textExtents;
    }

    @Override
    public Color getGray() {
        return gray;
    }

    @Override
    public void setFont(Font arg0) {
        super.setFont(arg0);
        textExtents = getTextExtents();
        if (listener != null) listener.resized();
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (listener != null) listener.resized();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (listener != null) listener.resized();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setHeatmap(RenderedHeatmap<?> heatmap) {
        this.heatmap = (RenderedHeatmap<Image>)heatmap;
        if (this.isDisposed()) return;
        getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (!CanvasSWT.this.isDisposed()) redraw();
            }
        });
    }

    @Override
    public void setListener(CanvasListener listener) {
        this.listener = listener;
    }

    /**
     * Gets the text extents.
     * 
     * @return the text extents
     */
    private Dimension getTextExtents() {
        GC gc = new GC(this);
        int width = 2 * gc.textExtent(Graphics.POSTFIX).x;
        int height = gc.textExtent(Graphics.POSTFIX).y;
        return new Dimension(width, height);
    }

    /**
     * Sets the canvas size.
     * 
     * @param size the new canvas size
     */
    private void setCanvasSize(Point size) {
        this.size = size;
    }
}
