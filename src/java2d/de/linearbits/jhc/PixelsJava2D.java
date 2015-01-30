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
