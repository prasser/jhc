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
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

/**
 * This class implements Pixels for SWT
 * 
 * @author Fabian Prasser
 */
class PixelsSWT implements Pixels<Image> {

    /** The buffer. */
    private byte[]          buffer;

    /** The display. */
    private final Display   display;

    /** The image. */
    private Image           image;

    /** The size. */
    private final Dimension size;

    /** The source. */
    private ImageData       source;

    /**
     * Creates a new instance
     * 
     * @param size the size
     * @param display the display
     * @param initialColor the initial color
     */
    protected PixelsSWT(Dimension size, Display display, int initialColor) {

        // Init
        this.size = size;
        this.display = display;

        // Create buffer
        this.buffer = new byte[size.height * size.width * 4];
        this.source = new ImageData(size.width, size.height, 32, new PaletteData(0x00ff0000, 0x0000ff00, 0x000000ff), 1, buffer);

        // TODO: Potentially inefficient
        if (initialColor != 0) {
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++) {
                    set(x, y, initialColor);
                }
            }
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
        source.setPixel(x, size.height - 1 - y, val);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.Pixels#update()
     */
    @Override
    public void update() {
        if (this.image != null) this.image.dispose();
        this.image = new Image(display, source);
    }
}
