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
import java.awt.Toolkit;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;
import java.util.Arrays;

/**
 * This class implements the pixels interface for Java2D
 * 
 * @author Fabian Prasser
 */
class PixelsJava2D implements Pixels<Image> {

    /** The buffer. */
    private int[]             buffer;

    /** The image. */
    private Image             image;

    /** The size. */
    private final Dimension   size;

    /** The source. */
    private MemoryImageSource source;

    /**
     * Instantiates a new pixels java2 d.
     * 
     * @param size the size
     * @param initialColor the initial color
     */
    protected PixelsJava2D(Dimension size, int initialColor) {

        // Init
        this.size = size;

        // Create buffer
        this.buffer = new int[size.height * size.width];
        this.source = new MemoryImageSource(size.width, size.height, new DirectColorModel(24, 0xff0000, 0xff00, 0xff), this.buffer, 0, size.width);
        this.source.setAnimated(true);
        this.image = Toolkit.getDefaultToolkit().createImage(source);

        // TODO: Potentially inefficient
        if (initialColor != 0) {
            Arrays.fill(this.buffer, initialColor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Pixels#getImage()
     */
    @Override
    public Image getImage() {
        return image;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Pixels#getSize()
     */
    @Override
    public Dimension getSize() {
        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Pixels#set(int, int, int)
     */
    @Override
    public void set(int x, int y, int val) {
        this.buffer[(size.height - 1 - y) * size.width + x] = val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Pixels#update()
     */
    @Override
    public void update() {
        this.source.newPixels();
    }
}
