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
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;

/**
 * This class implements a tooltip for AWT
 * @author Fabian Prasser
 */
class TooltipAWT extends Window {

    /** SUID*/
    private static final long serialVersionUID = 7979764400739243305L;
    /** Text to display*/
    private String            text             = null;
    /** Image to render into*/
    private BufferedImage     image            = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    /** Screen size*/
    private java.awt.Rectangle screenSize;

    /**
     * Creates a new instance
     */
    protected TooltipAWT() {
        super(new Frame());
        this.setLayout(new FlowLayout());
        this.setAlwaysOnTop(true);
        this.setSize(0, 0);
        this.setLocation(-10, -10);
        this.screenSize = getScreenBounds(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hideTooltip();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                hideTooltip();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                hideTooltip();
            }
        });
    }

    /**
     * Hides the tooltip
     */
    protected void hideTooltip() {
        this.text = null;
        this.setSize(0, 0);
        this.setLocation(-10, -10);
    }

    @Override
    public void paint(Graphics g) {
        if (text != null && image.getWidth() > 1 && image.getHeight() > 1) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Shows the tooltip
     * @param x
     * @param y
     * @param text
     */
    protected void showTooltip(int x, int y, String text) {

        if (text == null) {
            hideTooltip();
        } else {
            if (!this.isVisible()) this.setVisible(true);
            boolean repaint = false;
            if (!text.equals(this.text)) {
                this.text = text;
                this.paintBuffer();
                repaint = true;
            }
            this.setLocation(x, y);
            if (this.getWidth() != image.getWidth() || this.getHeight() != image.getHeight()) {
                this.setSize(image.getWidth(), image.getHeight());
                repaint();
            } else if (repaint) {
                repaint();
            }
        }
    }

    /**
     * Helper
     * @param gc
     * @param string
     * @return
     */
    private int getTextHeight(Graphics2D gc, String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        LineMetrics lm = gc.getFont().getLineMetrics(string, frc);
        return Math.round(lm.getDescent() + lm.getAscent());
    }

    /**
     * Gets the text offset.
     * 
     * @param string the string
     * @return the text offset
     */
    private int getTextOffset(Graphics2D gc, String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        LineMetrics lm = gc.getFont().getLineMetrics(string, frc);
        return Math.round(lm.getAscent());
    }

    /**
     * Helper
     * @param gc
     * @param string
     * @return
     */
    private int getTextWidth(Graphics2D gc, String string) {

        FontRenderContext frc = gc.getFontRenderContext();
        return (int) Math.round(gc.getFont().getStringBounds(string, frc).getWidth());
    }

    /**
     * Paints the tooltip
     */
    private void paintBuffer() {

        Graphics2D g = (Graphics2D) image.getGraphics();
        String lines[] = text.split("\\r?\\n");

        int height = 0;
        int width = 0;
        int offset = getTextOffset(g, text);
        for (String line : lines) {
            height += getTextHeight(g, line);
            width = Math.max(width, getTextWidth(g, line));
        }

        height += 4;
        width += 4;
        
        height = Math.min(screenSize.height, height);
        width = Math.min(screenSize.width, width);

        if (image.getWidth() != width || image.getHeight() != height) {
            g.dispose();
            image.flush();
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = (Graphics2D) image.getGraphics();
        }

        g.setColor(Color.white);
        g.fillRect(0, 0, width - 1, height - 1);

        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        
        int y = 2;
        for (int i = 0; i < lines.length; i++) {

            g.drawString(lines[i], 2, y + offset);
            y += (height - 4) / lines.length;
        }

        g.dispose();
    }
    
    /**
     * See http://stackoverflow.com/questions/1936566/how-do-you-get-the-screen-width-in-java
     * @param wnd
     * @return
     */
    private static java.awt.Rectangle getScreenBounds(Window wnd) {
        java.awt.Rectangle                           sb;
        Insets                              si=getScreenInsets(wnd);

        if(wnd==null) { 
            sb=GraphicsEnvironment
               .getLocalGraphicsEnvironment()
               .getDefaultScreenDevice()
               .getDefaultConfiguration()
               .getBounds(); 
            }
        else { 
            sb=wnd
               .getGraphicsConfiguration()
               .getBounds(); 
            }

        sb.x     +=si.left;
        sb.y     +=si.top;
        sb.width -=si.left+si.right;
        sb.height-=si.top+si.bottom;
        return sb;
        }
    
    /**
     * See http://stackoverflow.com/questions/1936566/how-do-you-get-the-screen-width-in-java
     * @param wnd
     * @return
     */
    private static Insets getScreenInsets(Window wnd) {
        Insets                              si;

        if(wnd==null) { 
            si=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment
               .getLocalGraphicsEnvironment()
               .getDefaultScreenDevice()
               .getDefaultConfiguration()); 
            }
        else { 
            si=wnd.getToolkit().getScreenInsets(wnd.getGraphicsConfiguration()); 
            }
        return si;
        }
}
