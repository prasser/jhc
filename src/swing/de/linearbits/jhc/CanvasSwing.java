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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

/**
 * This class implements a canvas for swing
 * 
 * @author Fabian Prasser
 */
class CanvasSwing extends JComponent implements Canvas<Image, Font, Color> {

    /** The Constant serialVersionUID. */
    private static final long                 serialVersionUID = -3788402111642606157L;

    /** The black. */
    private Color                             black;

    /** The gray. */
    private Color                             gray;

    /** The heatmap. */
    private RenderedHeatmap<Image>            heatmap          = null;

    /** The listener. */
    private CanvasListener                    listener         = null;

    /** The painter. */
    private final Painter<Image, Font, Color> painter          = new Painter<Image, Font, Color>(this);

    /** The text extents. */
    private Dimension                         textExtents      = null;

    /** The tooltip*/
    private ToolTip                           tooltip          = new ToolTip();

    /**
     * Creates a new instance
     */
    protected CanvasSwing() {

        this.black = new Color(0, 0, 0);
        this.gray = new Color(128, 128, 128);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                if (listener != null) listener.resized();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                if (listener != null) listener.shown();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                if (heatmap != null && painter != null) {
                    String text = tooltip.getToolTip(heatmap, painter, e.getX(), e.getY(), true, false);
                    CanvasSwing.this.setToolTipText(text);
                } else {
                    CanvasSwing.this.setToolTipText(null);
                }
            }
        });

        this.setDoubleBuffered(true);
    }

    @Override
    public Color getBlack() {
        return black;
    }

    @Override
    public Dimension getCanvasSize() {
        return new Dimension(getWidth(), getHeight());
    }

    @Override
    public Dimension getExtents() {
        if (textExtents == null) {
            textExtents = getTextExtents();
        }
        return textExtents;
    }

    @Override
    public Color getGray() {
        return gray;
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
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
        this.repaint();
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
        FontMetrics metrics = this.getFontMetrics(this.getFont());
        int height = metrics.getHeight();
        int width = 2 * metrics.stringWidth(de.linearbits.jhc.Graphics.POSTFIX);
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (painter != null) {
            GraphicsJava2D graphics = new GraphicsJava2D(this, (Graphics2D) g);
            painter.paint(graphics, heatmap, this.getCanvasSize());
        }
    }
}
