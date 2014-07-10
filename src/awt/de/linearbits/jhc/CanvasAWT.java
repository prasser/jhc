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
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

/**
 * This class implements a canvas for AWT
 * 
 * @author Fabian Prasser
 */
class CanvasAWT extends Component implements Canvas<Image, Font, Color> {

    /** The Constant serialVersionUID. */
    private static final long                 serialVersionUID = 6100042540949178860L;

    /** The black. */
    private Color                             black;

    /** The buffer image. */
    private BufferedImage                     buffer           = null;

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

    /** The tooltip */
    private TooltipAWT                        tooltip          = new TooltipAWT();

    /** The tool tip manager*/
    private ToolTip                           tooltipmanager   = new ToolTip();

    /**
     * Creates a new instance
     */
    protected CanvasAWT() {

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
                    String text = tooltipmanager.getToolTip(heatmap, painter, e.getX(), e.getY(), false, false);
                    Point location = e.getPoint();
                    SwingUtilities.convertPointToScreen(location, CanvasAWT.this);
                    tooltip.showTooltip(location.x + 1, location.y + 1, text);
                } else {
                    tooltip.hideTooltip();
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
        return new Dimension(getWidth(), getHeight());
    }

    @Override
    public Dimension getExtents() {
        if (this.textExtents == null) {
            this.textExtents = getTextExtents();
        }
        return this.textExtents;
    }

    @Override
    public Color getGray() {
        return gray;
    }

    @Override
    public void paint(Graphics g) {

        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            resetBuffer();
        }

        Graphics g2 = buffer.getGraphics();
        paintBuffer(g2);
        g2.dispose();
        g.drawImage(buffer, 0, 0, this);
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

    @Override
    public void update(Graphics g) {
        paint(g);
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

    /**
     * Paint buffer.
     * 
     * @param g the g
     */
    private void paintBuffer(Graphics g) {
        if (painter != null) {
            GraphicsJava2D graphics = new GraphicsJava2D(this, (Graphics2D) g);
            painter.paint(graphics, heatmap, this.getCanvasSize());
        } else {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Reset buffer.
     */
    private void resetBuffer() {

        if (buffer != null) {
            buffer.flush();
        }

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }
}
