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

/**
 * This class paints the heatmap via a generic graphics object
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 * @param <U> the generic type
 * @param <V> the value type
 */
class Painter<T, U, V> {

    /** The canvas. */
    private final Canvas<T, U, V> canvas;

    /** The gradient. */
    private JHCGradient           gradient;

    /** The insets. */
    private Rectangle             heatmapPosition = null;

    /** The position of the legend. */
    private Rectangle             legendPosition  = null;

    /** The legend. */
    private T                     legend;

    /**
     * Instantiates a new painter.
     * 
     * @param canvas the canvas
     */
    protected Painter(Canvas<T, U, V> canvas) {
        this.canvas = canvas;
    }

    /**
     * Gets the insets.
     * 
     * @return the heatmap position
     */
    protected Rectangle getHeatmapPosition() {
        return heatmapPosition;
    }

    /**
     * Gets the legend position.
     * 
     * @return the legend position
     */
    protected Rectangle getLegendPosition() {
        return legendPosition;
    }
    /**
     * Paint.
     * 
     * @param graphics the graphics
     * @param heatmap the heatmap
     * @param size the size
     */
    protected void paint(Graphics<T, V> graphics, RenderedHeatmap<T> heatmap, Dimension size) {
        
        if (heatmap == null ||
            heatmap == RenderedHeatmap.NO_DATA || 
            heatmap == RenderedHeatmap.TOO_SMALL) {

            graphics.init();

            // Clear
            graphics.setBackground(canvas.getBackground());
            graphics.drawRectangleFilled(0, 0, size.width, size.height);
            graphics.setForeground(canvas.getForeground());
            
            String message = "No data";
            if (heatmap == RenderedHeatmap.TOO_SMALL) {
                message = "No space";
            }
            graphics.drawStringCentered(message, 0, 0, size.width, size.height);

            // Return
            return;
        }
        

        // Layout
        JHCConfiguration config = heatmap.getConfig();
        RenderedLayout layout = config.getLayout().getRenderedLayout(config, canvas, size, heatmap);

        graphics.init();

        // Prepare legend
        if (legend == null || config.getGradient() != gradient) {
            if (legend != null) {
                disposeLegend(this.legend);
            }
            this.legend = graphics.drawLegend(config.getGradient());
            this.gradient = config.getGradient();
        }

        int width = layout.getWidth();
        int height = layout.getHeight();
        int x = layout.getX();
        int y = layout.getY();
        int textOffset = layout.getTextOffset();
        double xTickOffset = layout.getxTickOffset();
        double yTickOffset = layout.getyTickOffset();
        boolean drawXTicks = layout.isDrawXTicks();
        boolean drawYTicks = layout.isDrawYTicks();
        boolean xTicksRotate = layout.isXTicksRotate();
        boolean yTicksRotate = layout.isYTicksRotate();
        int xTicksSpace = layout.getXTicksSpace();
        int yTicksSpace = layout.getYTicksSpace();

        if (width <= 0 || height <= 0) {

            // Clear
            graphics.setBackground(canvas.getBackground());
            graphics.drawRectangleFilled(0, 0, size.width, size.height);
            heatmapPosition = null;

            // Return
            return;
        }

        // Offsets and ticks
        int xAxisLabelOffset = y + height + textOffset;
        int yAxisLabelOffset = x - textOffset;
        int smallTickLength = layout.getSmallTickLength();
        int largeTickLength = layout.getLargeTickLength();
        xTickOffset = xTickOffset >= 2 ? xTickOffset : 2;
        yTickOffset = yTickOffset >= 2 ? yTickOffset : 2;

        // Legend
        int centerOffset = layout.getCenterOffset();
        int legendWidth = layout.getLegendWidth();
        double legendTicksOffset = (double) height / (double) gradient.getSteps();
        legendTicksOffset = legendTicksOffset >= 2 ? legendTicksOffset : 2;

        // Clear
        graphics.setBackground(canvas.getBackground());
        graphics.drawRectangleFilled(0, 0, size.width, size.height);

        // Heatmap
        graphics.disableAntialiasing();
        graphics.disableInterpolation();
        graphics.drawImage(heatmap.getImage(), x, y, width, height);
        graphics.resetAntialiasing();
        graphics.resetInterpolation();

        // Small X-ticks
        if (xTickOffset >= 2) {
            graphics.setForeground(canvas.getGray());
            double xTickPosition = x;
            for (int i = 0; i < heatmap.getWidth(); i++) {
                int xTickPositionAsInt = (int) Math.round(xTickPosition);
                xTickPositionAsInt = xTickPositionAsInt < x + width ? xTickPositionAsInt : x + width;
                graphics.drawLine(xTickPositionAsInt, y + height, xTickPositionAsInt, y + height + smallTickLength);
                graphics.drawLine(xTickPositionAsInt, y, xTickPositionAsInt, y - smallTickLength);
                xTickPosition += xTickOffset;
            }
        }

        // X-tick labels
        if (drawXTicks) {

            if (!xTicksRotate) {
                graphics.setForeground(canvas.getForeground());
                double xTickPosition = x;
                int xTickWidth = (int) xTickOffset;
                for (int i = 0; i < heatmap.getWidth(); i++) {
                    int xTickPositionAsInt = (int) Math.round(xTickPosition);
                    graphics.drawStringBelowHorizontallyCentered(heatmap.getXLabel(i), xTickPositionAsInt, y + height + textOffset, xTickWidth);
                    xTickPosition += xTickOffset;
                }
                graphics.resetClipping();
                xAxisLabelOffset += textOffset + graphics.getTextHeight(config.getXLabel());
            } else {

                graphics.setRotation(-90);
                double xTickPosition = x;
                graphics.setForeground(canvas.getForeground());
                int xTickWidth = (int) xTickOffset;
                for (int i = 0; i < heatmap.getWidth(); i++) {

                    int xTickPositionAsInt = (int) Math.round(xTickPosition);
                    String label = heatmap.getXLabel(i);
                    int offset = Math.min(xTicksSpace, graphics.getTextWidth(label));
                    graphics.drawStringVerticallyCenteredLeftAligned(label, -(y + height + textOffset + offset), xTickPositionAsInt, xTicksSpace, xTickWidth);
                    xTickPosition += xTickOffset;
                }
                graphics.resetRotation();
                graphics.resetClipping();
                xAxisLabelOffset += xTicksSpace;
            }
        }

        // Small Y-ticks
        if (yTickOffset >= 2) {
            double yTickPosition = y + height;
            graphics.setForeground(canvas.getGray());
            for (int i = 0; i < heatmap.getHeight(); i++) {
                int yTickPositionAsInt = (int) Math.round(yTickPosition);
                yTickPositionAsInt = yTickPositionAsInt >= y ? yTickPositionAsInt : y;
                graphics.drawLine(x, yTickPositionAsInt, x - smallTickLength, yTickPositionAsInt);
                graphics.drawLine(x + width, yTickPositionAsInt, x + width + smallTickLength, yTickPositionAsInt);
                yTickPosition -= yTickOffset;
            }
        }

        // Y-tick labels
        if (drawYTicks) {

            if (!yTicksRotate) {
                graphics.setRotation(-90);
                double yTickPosition = y + height;
                graphics.setForeground(canvas.getForeground());
                int yTickWidth = (int) yTickOffset;
                for (int i = 0; i < heatmap.getHeight(); i++) {
                    int yTickPositionAsInt = (int) Math.round(yTickPosition);
                    graphics.drawStringAboveHorizontallyCentered(heatmap.getYLabel(i), -yTickPositionAsInt, x - textOffset, yTickWidth);
                    yTickPosition -= yTickOffset;
                }
                graphics.resetRotation();
                graphics.resetClipping();
                yAxisLabelOffset -= textOffset + graphics.getTextHeight(config.getYLabel());
            } else {

                double yTickPosition = y + height - yTickOffset;
                int yTickX = x - textOffset - yTicksSpace;
                graphics.setForeground(canvas.getForeground());
                for (int i = 0; i < heatmap.getHeight(); i++) {
                    int yTickPositionAsInt = (int) Math.round(yTickPosition);
                    String label = heatmap.getYLabel(i);
                    int offset = yTicksSpace - Math.min(yTicksSpace, graphics.getTextWidth(label));
                    graphics.drawStringVerticallyCenteredLeftAligned(heatmap.getYLabel(i), yTickX + offset, yTickPositionAsInt, yTicksSpace, (int) yTickOffset);
                    yTickPosition -= yTickOffset;
                }
                graphics.resetClipping();
                yAxisLabelOffset -= yTicksSpace;
            }
        }

        // Large ticks
        graphics.setForeground(canvas.getBlack());
        graphics.drawLine(x, y - largeTickLength, x, y + height + largeTickLength);
        graphics.drawLine(x + width, y - largeTickLength, x + width, y + height + largeTickLength);
        graphics.drawLine(x - largeTickLength, y, x + width + largeTickLength, y);
        graphics.drawLine(x - largeTickLength, y + height, x + width + largeTickLength, y + height);

        // Legend ticks
        int legendX = x + width + centerOffset;
        double legendTicksPosition = y;
        graphics.setForeground(canvas.getGray());
        for (int i = 0; i < gradient.getSteps(); i++) {
            int position = (int) Math.round(legendTicksPosition);
            position = position <= y + height ? position : y + height;
            graphics.drawLine(legendX - smallTickLength, position, legendX + legendWidth + smallTickLength, position);
            legendTicksPosition += legendTicksOffset;
        }

        // Legend
        graphics.enableAntialiasing();
        graphics.enableInterpolation();

        graphics.drawImage(legend, legendX, y, legendWidth, height);
        graphics.resetAntialiasing();
        graphics.resetInterpolation();

        // Legend border
        graphics.setForeground(canvas.getBlack());
        graphics.drawLine(legendX, y - largeTickLength, legendX, y + height + largeTickLength);
        graphics.drawLine(legendX + legendWidth, y - largeTickLength, legendX + legendWidth, y + height + largeTickLength);
        graphics.drawLine(legendX - largeTickLength, y, legendX + legendWidth + largeTickLength, y);
        graphics.drawLine(legendX - largeTickLength, y + height, legendX + legendWidth + largeTickLength, y + height);

        // Legend labels
        graphics.setForeground(canvas.getForeground());
        graphics.drawStringAboveHorizontallyCentered("Max", legendX, y - textOffset, legendWidth);
        graphics.drawStringBelowHorizontallyCentered("Min", legendX + 1, y + height + textOffset, legendWidth);
        graphics.resetClipping();

        // X-axis label
        graphics.drawStringBelowHorizontallyCentered(config.getXLabel(), x, xAxisLabelOffset, width);

        // Y-axis label
        graphics.setRotation(-90);
        graphics.drawStringAboveHorizontallyCentered(config.getYLabel(), -(y + height), yAxisLabelOffset, height);
        graphics.resetRotation();
        graphics.resetClipping();

        // Store insets
        heatmapPosition = new Rectangle(x, y, width, height);
        legendPosition = new Rectangle(legendX, y, legendWidth, height);
    }

	protected void disposeLegend(T legend) {
		// Overwritten by SWT version
	}
	
	protected void dispose(){
		if (legend != null) {
			disposeLegend(legend);
		}
	}
}
