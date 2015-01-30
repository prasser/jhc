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
