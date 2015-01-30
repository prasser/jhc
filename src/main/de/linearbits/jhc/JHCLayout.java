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

/**
 * This class represents the layout that is to be used when displaying the heatmap
 * 
 * @author Fabian Prasser
 */
public class JHCLayout {

	/** The Constant MINIMAL_HEATMAP_SIZE. */
	private static final int MINIMAL_HEATMAP_SIZE = 10;

	/** The Constant DEFAULT_LARGE_TICK_LENGTH. */
	private static final int DEFAULT_LARGE_TICK_LENGTH = 5;

	/** The Constant DEFAULT_LEGEND_WIDTH. */
	private static final int DEFAULT_LEGEND_WIDTH = 30;

	/** The Constant DEFAULT_OFFSET_CENTER_RIGHT_TOP. */
	private static final int DEFAULT_OFFSET_CENTER_RIGHT_TOP = 30;

	/** The Constant DEFAULT_OFFSET_LEFT_BOTTOM. */
	private static final int DEFAULT_OFFSET_LEFT_BOTTOM = 5;

	/** The Constant DEFAULT_SMALL_TICK_LENGTH. */
	private static final int DEFAULT_SMALL_TICK_LENGTH = 3;

	/** The Constant DEFAULT_TEXT_OFFSET. */
	private static final int DEFAULT_TEXT_OFFSET = 5;

	/** The Constant DEFAULT_TICK_SPACE. */
	private static final int DEFAULT_TICK_SPACE = 20;

    /** The bottom offset. */
    private final int        bottomOffset;

    /** The center offset. */
    private final int        centerOffset;

    /** The large tick length. */
    private final int        largeTickLength;

    /** The left offset. */
    private final int        leftOffset;

    /** The legend width. */
    private final int        legendWidth;

    /** The right offset. */
    private final int        rightOffset;

    /** The small tick length. */
    private final int        smallTickLength;

    /** The text offset. */
    private final int        textOffset;

    /** The top offset. */
    private final int        topOffset;

    /** The x tick rotate. */
    private final boolean    xTickRotate;

    /** The x tick space. */
    private final int        xTickSpace;

    /** The y tick rotate. */
    private final boolean    yTickRotate;

    /** The y tick space. */
    private final int        yTickSpace;

    /**
     * Instantiates a new layout.
     */
    public JHCLayout() {
        this.leftOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.rightOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.topOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.bottomOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.centerOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.legendWidth = DEFAULT_LEGEND_WIDTH;
        this.largeTickLength = DEFAULT_LARGE_TICK_LENGTH;
        this.smallTickLength = DEFAULT_SMALL_TICK_LENGTH;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = false;
        this.yTickRotate = false;
        this.xTickSpace = DEFAULT_TICK_SPACE;
        this.yTickSpace = DEFAULT_TICK_SPACE;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param xTickRotate the x tick rotate
     * @param xTickSpace the x tick space
     * @param yTickRotate the y tick rotate
     * @param yTickSpace the y tick space
     */
    public JHCLayout(boolean xTickRotate, int xTickSpace, boolean yTickRotate, int yTickSpace) {
        this.leftOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.rightOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.topOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.bottomOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.centerOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.legendWidth = DEFAULT_LEGEND_WIDTH;
        this.largeTickLength = DEFAULT_LARGE_TICK_LENGTH;
        this.smallTickLength = DEFAULT_SMALL_TICK_LENGTH;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = xTickRotate;
        this.yTickRotate = yTickRotate;
        this.xTickSpace = xTickSpace;
        this.yTickSpace = yTickSpace;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param legendWidth the legend width
     * @param largeTickLength the large tick length
     * @param smallTickLength the small tick length
     */
    public JHCLayout(int legendWidth, int largeTickLength, int smallTickLength) {
        this.leftOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.rightOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.topOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.bottomOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.centerOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.legendWidth = legendWidth;
        this.smallTickLength = smallTickLength;
        this.largeTickLength = largeTickLength;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = false;
        this.yTickRotate = false;
        this.xTickSpace = DEFAULT_TICK_SPACE;
        this.yTickSpace = DEFAULT_TICK_SPACE;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param legendWidth the legend width
     * @param largeTickLength the large tick length
     * @param smallTickLength the small tick length
     * @param xTickRotate the x tick rotate
     * @param xTickSpace the x tick space
     * @param yTickRotate the y tick rotate
     * @param yTickSpace the y tick space
     */
    public JHCLayout(int legendWidth, int largeTickLength, int smallTickLength, boolean xTickRotate, int xTickSpace, boolean yTickRotate, int yTickSpace) {
        this.leftOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.rightOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.topOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.bottomOffset = DEFAULT_OFFSET_LEFT_BOTTOM;
        this.centerOffset = DEFAULT_OFFSET_CENTER_RIGHT_TOP;
        this.legendWidth = legendWidth;
        this.smallTickLength = smallTickLength;
        this.largeTickLength = largeTickLength;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = xTickRotate;
        this.yTickRotate = yTickRotate;
        this.xTickSpace = xTickSpace;
        this.yTickSpace = yTickSpace;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param offsetLeft the offset left
     * @param offsetRight the offset right
     * @param offsetTop the offset top
     * @param offsetBottom the offset bottom
     * @param offsetCenter the offset center
     */
    public JHCLayout(int offsetLeft, int offsetRight, int offsetTop, int offsetBottom, int offsetCenter) {
        this.leftOffset = offsetLeft;
        this.rightOffset = offsetRight;
        this.topOffset = offsetTop;
        this.bottomOffset = offsetBottom;
        this.centerOffset = offsetCenter;
        this.legendWidth = DEFAULT_LEGEND_WIDTH;
        this.largeTickLength = DEFAULT_LARGE_TICK_LENGTH;
        this.smallTickLength = DEFAULT_SMALL_TICK_LENGTH;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = false;
        this.yTickRotate = false;
        this.xTickSpace = DEFAULT_TICK_SPACE;
        this.yTickSpace = DEFAULT_TICK_SPACE;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param offsetLeft the offset left
     * @param offsetRight the offset right
     * @param offsetTop the offset top
     * @param offsetBottom the offset bottom
     * @param offsetCenter the offset center
     */
    public JHCLayout(int offsetLeft, int offsetRight, int offsetTop, int offsetBottom, int offsetCenter, int offsetText) {
        this.leftOffset = offsetLeft;
        this.rightOffset = offsetRight;
        this.topOffset = offsetTop;
        this.bottomOffset = offsetBottom;
        this.centerOffset = offsetCenter;
        this.legendWidth = DEFAULT_LEGEND_WIDTH;
        this.largeTickLength = DEFAULT_LARGE_TICK_LENGTH;
        this.smallTickLength = DEFAULT_SMALL_TICK_LENGTH;
        this.textOffset = offsetText;
        this.xTickRotate = false;
        this.yTickRotate = false;
        this.xTickSpace = DEFAULT_TICK_SPACE;
        this.yTickSpace = DEFAULT_TICK_SPACE;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param offsetLeft the offset left
     * @param offsetRight the offset right
     * @param offsetTop the offset top
     * @param offsetBottom the offset bottom
     * @param offsetCenter the offset center
     * @param xTickRotate the x tick rotate
     * @param xTickSpace the x tick space
     * @param yTickRotate the y tick rotate
     * @param yTickSpace the y tick space
     */
    public JHCLayout(int offsetLeft, int offsetRight, int offsetTop, int offsetBottom, int offsetCenter, boolean xTickRotate, int xTickSpace, boolean yTickRotate, int yTickSpace) {
        this.leftOffset = offsetLeft;
        this.rightOffset = offsetRight;
        this.topOffset = offsetTop;
        this.bottomOffset = offsetBottom;
        this.centerOffset = offsetCenter;
        this.legendWidth = DEFAULT_LEGEND_WIDTH;
        this.largeTickLength = DEFAULT_LARGE_TICK_LENGTH;
        this.smallTickLength = DEFAULT_SMALL_TICK_LENGTH;
        this.textOffset = DEFAULT_TEXT_OFFSET;
        this.xTickRotate = xTickRotate;
        this.yTickRotate = yTickRotate;
        this.xTickSpace = xTickSpace;
        this.yTickSpace = yTickSpace;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param offsetLeft the offset left
     * @param offsetRight the offset right
     * @param offsetTop the offset top
     * @param offsetBottom the offset bottom
     * @param offsetCenter the offset center
     * @param minimalTextWidth the minimal text width
     * @param legendWidth the legend width
     * @param largeTickLength the large tick length
     * @param smallTickLength the small tick length
     * @param textOffset the text offset
     */
    public JHCLayout(int offsetLeft, int offsetRight, int offsetTop, int offsetBottom, int offsetCenter, int minimalTextWidth, int legendWidth, int largeTickLength, int smallTickLength, int textOffset) {
        this.leftOffset = offsetLeft;
        this.rightOffset = offsetRight;
        this.topOffset = offsetTop;
        this.bottomOffset = offsetBottom;
        this.centerOffset = offsetCenter;
        this.legendWidth = legendWidth;
        this.smallTickLength = smallTickLength;
        this.largeTickLength = largeTickLength;
        this.textOffset = textOffset;
        this.xTickRotate = false;
        this.yTickRotate = false;
        this.xTickSpace = DEFAULT_TICK_SPACE;
        this.yTickSpace = DEFAULT_TICK_SPACE;
        this.check();
    }

    /**
     * Instantiates a new layout.
     * 
     * @param offsetLeft the offset left
     * @param offsetRight the offset right
     * @param offsetTop the offset top
     * @param offsetBottom the offset bottom
     * @param offsetCenter the offset center
     * @param minimalTextWidth the minimal text width
     * @param legendWidth the legend width
     * @param largeTickLength the large tick length
     * @param smallTickLength the small tick length
     * @param textOffset the text offset
     * @param xTickRotate the x tick rotate
     * @param xTickSpace the x tick space
     * @param yTickRotate the y tick rotate
     * @param yTickSpace the y tick space
     */
    public JHCLayout(int offsetLeft, int offsetRight, int offsetTop, int offsetBottom, int offsetCenter, int minimalTextWidth, int legendWidth, int largeTickLength, int smallTickLength, int textOffset, boolean xTickRotate, int xTickSpace, boolean yTickRotate, int yTickSpace) {
        this.leftOffset = offsetLeft;
        this.rightOffset = offsetRight;
        this.topOffset = offsetTop;
        this.bottomOffset = offsetBottom;
        this.centerOffset = offsetCenter;
        this.legendWidth = legendWidth;
        this.smallTickLength = smallTickLength;
        this.largeTickLength = largeTickLength;
        this.textOffset = textOffset;
        this.xTickRotate = xTickRotate;
        this.yTickRotate = yTickRotate;
        this.xTickSpace = xTickSpace;
        this.yTickSpace = yTickSpace;
        this.check();
    }

    /**
     * Gets the rendered layout.
     * 
     * @param config the config
     * @param canvas the canvas
     * @param data the data
     * @return the rendered layout
     */
    protected RenderedLayout getRenderedLayout(JHCConfiguration config, Canvas<?, ?, ?> canvas, JHCData data) {
        return getRenderedLayout(config, canvas, canvas.getCanvasSize(), new Dimension(data.getWidth(), data.getHeight()));
    }

    /**
     * Gets the rendered layout.
     * 
     * @param config the config
     * @param canvas the canvas
     * @param size the size
     * @param heatmap the heatmap
     * @return the rendered layout
     */
    protected RenderedLayout getRenderedLayout(JHCConfiguration config, Canvas<?, ?, ?> canvas, Dimension size, RenderedHeatmap<?> heatmap) {
        return getRenderedLayout(config, canvas, size, new Dimension(heatmap.getWidth(), heatmap.getHeight()));
    }

    /**
     * Check.
     */
    private void check() {
        check("leftOffset", leftOffset);
        check("rightOffset", rightOffset);
        check("topOffset", topOffset);
        check("bottomOffset", bottomOffset);
        check("centerOffset", centerOffset);
        check("legendWidth", legendWidth);
        check("largeTickLength", largeTickLength);
        check("smallTickLength", smallTickLength);
        check("textOffset", textOffset);
        check("xTickSpace", xTickSpace);
        check("yTickSpace", yTickSpace);
    }

    /**
     * Check.
     * 
     * @param parameter the parameter
     * @param value the value
     */
    private void check(String parameter, int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Parameter '" + parameter + "' must be larger than zero");
        }
    }

    /**
     * Gets the rendered layout.
     * 
     * @param config the config
     * @param canvas the canvas
     * @param canvasSize the canvas size
     * @param dataSize the data size
     * @return the rendered layout
     */
    private RenderedLayout getRenderedLayout(JHCConfiguration config, Canvas<?, ?, ?> canvas, Dimension canvasSize, Dimension dataSize) {

        // Text Layout
        int postfixHeight = canvas.getExtents().height;
        int textOffset = getTextOffset();
        int minimalTextWidth = canvas.getExtents().width;
        int minimalTextHeight = canvas.getExtents().height;
        int labelOffsetTemp = 2 * textOffset + postfixHeight;

        // Size of heatmap
        int x = getLeftOffset() + labelOffsetTemp;
        int y = getTopOffset();
        int width = canvasSize.width - x - getCenterOffset() - getRightOffset() - getLegendWidth();
        int height = canvasSize.height - y - getBottomOffset() - labelOffsetTemp;

        if (width <= 0 || height <= 0) {

            // Return
            return new RenderedLayout(this, x, y, 0, 0, 0, 0, textOffset, 0, 0, false, false, false, 0, false, 0);
        }

        // Compute heatmap size in pixels
        Dimension maxSize = config.getMaximalSize();
        int heatWidth = dataSize.width;
        int heatHeight = dataSize.height;
        if (maxSize != null && heatWidth > maxSize.width) heatWidth = maxSize.width;
        if (maxSize != null && heatHeight > maxSize.height) heatHeight = maxSize.height;
        if (heatWidth > width) heatWidth = width;
        if (heatHeight > height) heatHeight = height;

        // Labels
        boolean drawXTicks = true;
        boolean drawYTicks = true;
        
        // Show both ticks
        if (isLayoutCanShowBothTicks(width, height, heatWidth, heatHeight, 
        		minimalTextHeight, minimalTextWidth, postfixHeight, textOffset)) {
        	
        	if (this.isXTickRotate() && this.isYTickRotate()) {
        		width -= this.getYTickSpace();
        		x+= this.getYTickSpace();
        		
        		height -= this.getXTickSpace();
        	} else if (!this.isXTickRotate() && this.isYTickRotate()) { 
        		width -= this.getYTickSpace();
        		x += this.getYTickSpace();
        		
        		height -= postfixHeight + textOffset;
        	} else if (this.isXTickRotate() && !this.isYTickRotate()) {
        		width -= postfixHeight + textOffset;
        		x+= postfixHeight + textOffset;
        		
        		height -= this.getXTickSpace();
        	} else if (!this.isXTickRotate() && !this.isYTickRotate()) {
        		width -= postfixHeight + textOffset;
        		x+= postfixHeight + textOffset;
        		
        		height -= postfixHeight + textOffset;
        	}
        	
        	drawXTicks = true;
        	drawYTicks = true;
         	
        // Show y ticks
        } else if (isLayoutCanShowYTicks(width, height, heatWidth, heatHeight, 
       		minimalTextHeight, minimalTextWidth, postfixHeight, textOffset)) {

       	if (this.isYTickRotate()) {
       		width -= this.getYTickSpace();
       		x += this.getYTickSpace();
            		
       	} else if (!this.isYTickRotate()) { 
       		width -= postfixHeight + textOffset;
       		x += postfixHeight + textOffset;
       	}

       	drawXTicks = false;
       	drawYTicks = true;
            	
        // Show x ticks
        } else if (isLayoutCanShowXTicks(width, height, heatWidth, heatHeight, 
        		minimalTextHeight, minimalTextWidth, postfixHeight, textOffset)) {
        	
        	if (this.isXTickRotate()) {	
        		height -= this.getXTickSpace();
        		
        	} else if (!this.isXTickRotate()) { 
        		height -= postfixHeight + textOffset;
        	} 

        	drawXTicks = true;
        	drawYTicks = false;
       
        // Show no ticks
        } else {
        	drawXTicks = false;
        	drawYTicks = false;
        }

        // Ticks
        double xTickOffset = (double) width / (double) heatWidth;
        double yTickOffset = (double) height / (double) heatHeight;

        // Return
        return new RenderedLayout(this, x, y, width, height, heatWidth, heatHeight, textOffset, xTickOffset, yTickOffset, drawXTicks, drawYTicks, isXTickRotate(), getXTickSpace(), isYTickRotate(), getYTickSpace());
    }
    
    /**
     * Layouting
     * @param width
     * @param height
     * @param heatWidth
     * @param heatHeight
     * @param minimalTextHeight
     * @param minimalTextWidth
     * @param postfixHeight
     * @param textOffset
     * @return
     */
    private boolean isLayoutCanShowBothTicks(double width, double height, double heatWidth, double heatHeight, 
    		int minimalTextHeight, int minimalTextWidth, int postfixHeight, int textOffset) {
    	
    	if (this.isXTickRotate() && this.isYTickRotate()) {
    		
    		width -= this.getYTickSpace();
    		height -= this.getXTickSpace();
    		
    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextHeight &&
    			width / heatWidth >= minimalTextHeight) {
    			return true;
    		}
    		
    	} else if (!this.isXTickRotate() && this.isYTickRotate()) { 
    		
    		width -= this.getYTickSpace();
    		height -= postfixHeight + textOffset;

    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextHeight &&
    			width / heatWidth >= minimalTextWidth) {
    			return true;
    		}
    		
    	} else if (this.isXTickRotate() && !this.isYTickRotate()) {

    		width -= postfixHeight + textOffset;
    		height -= this.getXTickSpace();

    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextWidth &&
    			width / heatWidth >= minimalTextHeight) {
    			return true;
    		}
    		
    	} else if (!this.isXTickRotate() && !this.isYTickRotate()) {

    		width -= postfixHeight + textOffset;
    		height -= postfixHeight + textOffset;

    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextWidth &&
    			width / heatWidth >= minimalTextWidth) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * Layouting
     * @param width
     * @param height
     * @param heatWidth
     * @param heatHeight
     * @param minimalTextHeight
     * @param minimalTextWidth
     * @param postfixHeight
     * @param textOffset
     * @return
     */
    private boolean isLayoutCanShowXTicks(double width, double height, double heatWidth, double heatHeight, 
    		int minimalTextHeight, int minimalTextWidth, int postfixHeight, int textOffset) {

    	if (this.isXTickRotate()) {
    		
    		height -= this.getXTickSpace();
    		
    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (width / heatWidth >= minimalTextHeight) {
    			return true;
    		}
    		
    	} else if (!this.isXTickRotate()) { 
    		
    		height -= postfixHeight + textOffset;

    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (width / heatWidth >= minimalTextWidth) {
    			return true;
    		}
    		
    	} 
    	return false;
    }

    /**
     * Layouting
     * @param width
     * @param height
     * @param heatWidth
     * @param heatHeight
     * @param minimalTextHeight
     * @param minimalTextWidth
     * @param postfixHeight
     * @param textOffset
     * @return
     */
    private boolean isLayoutCanShowYTicks(double width, double height, double heatWidth, double heatHeight, 
    		int minimalTextHeight, int minimalTextWidth, int postfixHeight, int textOffset) {

    	if (this.isYTickRotate()) {
    		
    		width -= this.getYTickSpace();
    		
    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextHeight) {
    			return true;
    		}
    		
    	} else if (!this.isYTickRotate()) { 
    		
    		width -= postfixHeight + textOffset;

    		if (width < MINIMAL_HEATMAP_SIZE || height < MINIMAL_HEATMAP_SIZE) {
    			return false;
    		}
    		
    		if (height / heatHeight >= minimalTextWidth) {
    			return true;
    		}
    		
    	} 
    	return false;
    }

    /**
     * Gets the bottom offset.
     * 
     * @return the bottom offset
     */
    protected int getBottomOffset() {
        return bottomOffset;
    }

    /**
     * Gets the center offset.
     * 
     * @return the center offset
     */
    protected int getCenterOffset() {
        return centerOffset;
    }

    /**
     * Gets the large tick length.
     * 
     * @return the large tick length
     */
    protected int getLargeTickLength() {
        return largeTickLength;
    }

    /**
     * Gets the left offset.
     * 
     * @return the left offset
     */
    protected int getLeftOffset() {
        return leftOffset;
    }

    /**
     * Gets the legend width.
     * 
     * @return the legend width
     */
    protected int getLegendWidth() {
        return legendWidth;
    }

    /**
     * Gets the right offset.
     * 
     * @return the right offset
     */
    protected int getRightOffset() {
        return rightOffset;
    }

    /**
     * Gets the small tick length.
     * 
     * @return the small tick length
     */
    protected int getSmallTickLength() {
        return smallTickLength;
    }

    /**
     * Gets the text offset.
     * 
     * @return the text offset
     */
    protected int getTextOffset() {
        return textOffset;
    }

    /**
     * Gets the top offset.
     * 
     * @return the top offset
     */
    protected int getTopOffset() {
        return topOffset;
    }

    /**
     * Gets the x tick space.
     * 
     * @return the x tick space
     */
    protected int getXTickSpace() {
        return xTickSpace;
    }

    /**
     * Gets the y tick space.
     * 
     * @return the y tick space
     */
    protected int getYTickSpace() {
        return yTickSpace;
    }

    /**
     * Checks if is x tick rotate.
     * 
     * @return true, if is x tick rotate
     */
    protected boolean isXTickRotate() {
        return xTickRotate;
    }

    /**
     * Checks if is y tick rotate.
     * 
     * @return true, if is y tick rotate
     */
    protected boolean isYTickRotate() {
        return yTickRotate;
    }
}
